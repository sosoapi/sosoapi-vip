package com.dev.proj.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

import com.dev.base.util.AuthCacheUtil;
import com.dev.proj.vo.ProjectRoleInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目标签</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月5日下午6:01:13</p>
 */
public abstract class ProjTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	/** 项目id*/
	private Long projId;
	
	/** 文档id*/
	private Long docId;
	
	/** 属性*/
    private String name;

    @Override
	public int doStartTag() throws JspException {
    	boolean show = showTagBody();
        if (show) {
            return TagSupport.EVAL_BODY_INCLUDE;
        } 
        else {
            return TagSupport.SKIP_BODY;
        }
	}

    /**
     * 
    		*@name 获取session 
    		*@Description  
    		*@CreateDate 2017年9月6日下午2:39:12
     */
    protected HttpSession getSession() {
		return pageContext.getSession();
	}
    
    /**
     * 
    		*@name 获取用户信息
    		*@Description  
    		*@CreateDate 2017年9月5日下午6:07:34
     */
    protected UserInfo getUserInfo() {
    	return AuthCacheUtil.getUserInfo(getSession());
	}
    
    /**
     * 
    		*@name 更新用户信息
    		*@Description  
    		*@CreateDate 2017年9月5日下午6:07:34
     */
    protected void saveUserInfo(UserInfo userInfo) {
    	AuthCacheUtil.saveUserInfo(getSession(), userInfo);
	}
    
    /**
     * 
    		*@name 获取项目角色信息
    		*@Description  
    		*@CreateDate 2017年9月7日下午4:55:43
     */
    protected ProjectRoleInfo getProjRoleInfo(Long projId,Long docId) {
    	Long projRoleId = AuthCacheUtil.getProjRoleId(getSession(), projId, docId);
    	return getProjRoleInfo(projRoleId);
	}
    
    /**
     * 
    		*@name 获取项目角色信息
    		*@Description  
    		*@CreateDate 2017年9月6日下午2:40:34
     */
    protected ProjectRoleInfo getProjRoleInfo(Long projRoleId) {
    	if (projRoleId == null) {
			return null;
		}
    	
    	return AuthCacheUtil.getProjectRoleInfo(getSession(), projRoleId);
	}
    
    /**
     * 
    		*@name 直接输出
    		*@Description  
    		*@CreateDate 2017年9月7日下午5:39:57
     */
    protected void printVal(String value) {
    	if (StringUtils.isEmpty(value)) {
			return ;
		}
    	
    	try {
			pageContext.getOut().write(value);
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * 
    		*@name 业务权限控制
    		*@Description  
    		*@CreateDate 2017年9月5日下午6:03:06
     */
    protected abstract boolean showTagBody();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}
}
