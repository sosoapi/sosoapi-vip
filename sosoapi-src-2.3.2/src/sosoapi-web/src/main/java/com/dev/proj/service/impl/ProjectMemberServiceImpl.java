package com.dev.proj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.exception.AuthException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.MailUtil;
import com.dev.base.util.Pager;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.proj.dao.ProjectMemberDao;
import com.dev.proj.entity.Project;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.proj.vo.ProjectMemberInfo;
import com.dev.user.entity.UserBasic;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目成员相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ProjectMemberServiceImpl extends BaseMybatisServiceImpl<ProjectMember,Long,ProjectMemberDao>
										implements ProjectMemberService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	public List<ProjectMemberInfo> listByProjId(Long projId, Long roleId,
									String nickName,String email,Pager pager) {
		nickName = getLikeExpr(nickName);
		email = getLikeExpr(email);
		
		List<Map> list = getMybatisDao().listByProjId(projId, roleId,nickName,email, pager);
		
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();
		for (Map info : list) {
			result.add(parseMemberInfo(info));
		}
		
		return result;
	}

	//组装成员信息
	private ProjectMemberInfo parseMemberInfo(Map info){
		ProjectMemberInfo result = new ProjectMemberInfo();
		result.setCreateDate((Date)info.get("createDate"));
		result.setEmail((String)info.get("email"));
		result.setNickName((String)info.get("nickName"));
		result.setUserId((Long)info.get("userId"));
		result.setProjRoleId(FormatUtils.parseLong(info.get("projRoleId")));
		result.setProjRoleName((String)info.get("projRoleName"));
		result.setProjNickName((String)info.get("projNickName"));
		
		return result;
	}
	
	@Override
	public int countByProjId(Long projId, Long roleId,String nickName,String email) {
		nickName = getLikeExpr(nickName);
		email = getLikeExpr(email);
		
		return getMybatisDao().countByProjId(projId, roleId,nickName,email);
	}

	@Override
	public void invite(Long userId, Long projId, String invitedEmail) {
		Map<String, Object> inviteInfo = MapUtils.newMap();
		inviteInfo.put("inviterId", userId);
		inviteInfo.put("projId", projId);
		inviteInfo.put("invitedEmail", invitedEmail);
		
		String code = CryptUtil.encryptAES(JsonUtils.toJson(inviteInfo),CfgConstants.SECURITY_SECRET_KEY);
		UserDetail detail = userDetailService.getByUserId(userId);
		Project project = projectService.getById(projId);
		Map<String, Object> model = MapUtils.newMap();
		model.put("email", invitedEmail);
		model.put("friendNickName", detail.getNickName());
		model.put("projName", project.getName());
		model.put("projAcceptUrl", CfgConstants.WEB_BASE_URL + "auth/proj/mem/accept.htm?code=" + code);
		
		MailUtil.send(invitedEmail, MailConstants.SUB_PROJ_MEM_INVITE, 
				MailConstants.TMPL_PROJ_MEM_INVITE, model);
	}

	@Override
	public Long accept(Long userId, String code,String projNickName) {
		Map<String, Object> inviteInfo = JsonUtils.toObject(CryptUtil.decryptAES(code,CfgConstants.SECURITY_SECRET_KEY), HashMap.class);
		UserBasic userBasic = userBasicService.getById(userId);
		
		String invitedEmail = (String)inviteInfo.get("invitedEmail");
		ValidateUtils.isTrue(RegexUtil.isEmail(invitedEmail), ErrorCode.SYS_001);
		
		Long projId = FormatUtils.parseLong(inviteInfo.get("projId"));
		//当前用户与被邀请的不是同一个
		if (!userBasic.getEmail().equals(invitedEmail)) {
			throw new AuthException();
		}
		
		accept(userId, projId, CfgConstants.PROJ_ROLE_ID_GUEST,projNickName);
		
		return projId;
	}

	@Override
	public void accept(Long userId, Long projId,Long roleId,String projNickName) {
		boolean isAccept = getMybatisDao().countRecord(userId, projId) > 0;
		if (!isAccept) {//未加入过，则加入到当前项目中
			ProjectMember projectMember = new ProjectMember();
			projectMember.setProjId(projId);
			projectMember.setUserId(userId);
			projectMember.setProjRoleId(roleId);
			projectMember.setProjNickName(projNickName);
			add(projectMember);
		}
	}
	
	@Override
	public void remove(Long currentId,Long userId, Long projId) {
		if (FormatUtils.isEqual(currentId, userId)) {//只有移除自己时才判断是否还有其他管理员
			validExistAdmin(userId,projId);
		}
		
		getMybatisDao().delByUserIdAndProjId(userId, projId);
	}


	@Override
	public void quit(Long userId, Long projId) {
		validExistAdmin(userId,projId);
		getMybatisDao().delByUserIdAndProjId(userId, projId);
	}
	
	@Override
	public List<ProjectInfo> listAuthProjectInfo(Long userId) {
		List<ProjectInfo> result = new ArrayList<ProjectInfo>();
		
		List<Map> infoList = getMybatisDao().listAuthProjectInfo(userId);
		ProjectInfo projectInfo = null;
		for (Map info : infoList) {
			projectInfo = new ProjectInfo();
			projectInfo.setProjId((Long)info.get("projId"));
			projectInfo.setDocId((Long)info.get("docId"));
			projectInfo.setProjRoleId(FormatUtils.parseLong(info.get("projRoleId")));
			
			result.add(projectInfo);
		}
		
		return result;
	}

	@Override
	public void updateRole(Long currentId,Long projId, Long userId, Long roleId,String projNickName) {
		//处理无创建者
		if (roleId != CfgConstants.PROJ_ROLE_ID_ADMIN && FormatUtils.isEqual(currentId, userId)) {
			validExistAdmin(userId,projId);
		}
		
		getMybatisDao().updateRole(projId, userId, roleId,projNickName);
	}

	@Override
	public ProjectMemberInfo getByUserIdAndProjId(Long userId, Long projId) {
		ProjectMemberInfo result = new ProjectMemberInfo();
		Map info = getMybatisDao().getByUserIdAndProjId(userId, projId);
		if (!CollectionUtils.isEmpty(info)) {
			result = parseMemberInfo(info);
		}
		
		return result;
	}

	@Override
	public List<String> listEmail(Long projId, Long roleId) {
		return getMybatisDao().listEmail(projId, roleId);
	}

	@Override
	public void sendNotice(UserInfo userInfo, Long projId, 
							String receiverRole,String otherReceivers,
								String title, String content) {
		List<String> emailList = parseReceiver(projId, receiverRole,otherReceivers);
		MailUtil.sendNotice(emailList, title, content);
	}
	
	//组装通知的用户列表
	private List<String> parseReceiver(Long projId,String receiverRole,String otherReceivers){
		Set<String> receiverSet = new HashSet<String>();
		Long roleId = null;
		if (!StringUtils.isEmpty(receiverRole)) {
			roleId = Long.parseLong(receiverRole);
			
		}
		receiverSet.addAll(listEmail(projId, roleId));
		
		if (!StringUtils.isEmpty(otherReceivers)) {
			String[] receiverArray = otherReceivers.split(";");
			for (String receiver : receiverArray) {
				if (RegexUtil.isEmail(receiver)) {
					receiverSet.add(receiver);
				}
			}
		}
		
		return new ArrayList<String>(receiverSet);
	}
	
	//保证至少有一个管理员
	private void validExistAdmin(Long userId,Long projId){
		List<Long> adminList = getMybatisDao().listUserIdByRoleId(projId, CfgConstants.PROJ_ROLE_ID_ADMIN);
		adminList.remove(userId);
		ValidateUtils.isTrue(adminList.size() > 0, ErrorCode.PROJ_001);
	}
	
	@Override
	public List<ProjectMemberInfo> listForAdd(Long userId, Long projId, Pager pager) {
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();
		List<Map> list = getMybatisDao().listForAdd(userId, projId, pager);
		for (Map info : list) {
			result.add(parseInfoForAdd(info));
		}
		
		return result;
	}

	@Override
	public int countForAdd(Long userId, Long projId) {
		return getMybatisDao().countForAdd(userId, projId);
	}

	@Override
	public List<ProjectMemberInfo> searchForAdd(Long projId, String email, boolean strictMatch, Pager pager) {
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();
		List<Map> list = getMybatisDao().searchForAdd(projId, email, strictMatch, pager);
		for (Map info : list) {
			result.add(parseInfoForAdd(info));
		}
		
		return result;
	}

	@Override
	public int countForSearchForAdd(Long projId, String email, boolean strictMatch) {
		return getMybatisDao().countForSearchForAdd(projId, email, strictMatch);
	}
	
	//组装用于新增的成员列表
	private ProjectMemberInfo parseInfoForAdd(Map info){
		ProjectMemberInfo memberInfo = new ProjectMemberInfo();
		memberInfo.setNickName((String)info.get("nickName"));
		memberInfo.setEmail((String)info.get("email"));
		memberInfo.setUserId((Long)info.get("userId"));
		memberInfo.setProjNickName((String)info.get("projNickName"));
		
		return memberInfo;
	}
	
	@Override
	public List<ProjectMemberInfo> listAllForAdd(Long projId, Pager pager) {
		List<ProjectMemberInfo> result = new ArrayList<ProjectMemberInfo>();
		List<Map> list = getMybatisDao().listAllForAdd(projId, pager);
		for (Map info : list) {
			result.add(parseInfoForAdd(info));
		}
		
		return result;
	}

	@Override
	public int countAllForAdd(Long projId) {
		return getMybatisDao().countAllForAdd(projId);
	}
	
	@Override
	public void add(Long inviterId, Long userId, Long projId, Long roleId,String projNickName) {
		accept(userId, projId, roleId,projNickName);
	}

	@Override
	public int countByUserIdAndDocId(Long userId, Long docId) {
		return getMybatisDao().countByUserIdAndDocId(userId, docId);
	}
}
