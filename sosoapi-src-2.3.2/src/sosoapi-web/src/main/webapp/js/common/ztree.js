function getFont(treeId, node) {
	return node.font ? node.font : {};
}

function showRemoveBtn(treeId, treeNode) {
	return treeNode.enableDel != false;
}

function showRenameBtn(treeId, treeNode) {
	return treeNode.enableEdit != false;
}

//获取根节点
function getParentNode(moveType,targetNode){
	var parentNode = null;
	if(moveType == "inner"){//成为子节点
		parentNode = targetNode;
	}
	else{//同级
		parentNode = targetNode.getParentNode();
	}
	
	return parentNode;
}

//根据层级验证是否允许拖拽
function validDropByLevel(treeNode,parentNode){
	var result = true;
	var parentLevel = 0;
	if(parentNode != null){//有父节点
		parentLevel = parentNode.level + 1;
	}
	
	if(treeNode.maxLevel != undefined
			&& parentLevel > treeNode.maxLevel){
		result = false;
	}
	
	if(treeNode.minLevel != undefined
			&& parentLevel < treeNode.minLevel){
		result = false;
	}
	
	return result;
}

//验证当前节点父节点是否允许移出子节点或内部排序
function validDropByParent(treeNode,targetParentNode){
	var result = true;
	var currentParentNode = treeNode.getParentNode();
	//移出
	if(currentParentNode != null 
			&& currentParentNode != targetParentNode
			&& currentParentNode.childOuter == false){
		result = false;
	}
	
	//内部排序
	if(currentParentNode != null 
			&& currentParentNode == targetParentNode
			&& currentParentNode.childOrder == false){
		result = false;
	}
	
	return result;
}

//验证目标父节点是否允许移进子节点
function validDropByTargetParent(treeNode,targetParentNode){
	var result = true;
	if(targetParentNode != null
			&& targetParentNode.dropInner == false){
		result = false;
	}
	
	return result;
}

function beforeDropCallback(treeId, treeNodes, targetNode, moveType, isCopy) {
	//当前第一个节点
	var treeNode = treeNodes[0];
	//目标位置父节点
	var parentNode = getParentNode(moveType,targetNode);
	
	//判断当前节点是否允许拖拽
	if(treeNode.drag == false 
			|| (targetNode != null && targetNode.dataId != undefined && targetNode.dataId < 0)){
		return false;
	}
	
	return validDropByLevel(treeNode,parentNode)
	       && validDropByParent(treeNode,parentNode)
	       && validDropByTargetParent(treeNode,parentNode);
}

//添加自定义
function addDiyDom(treeId, treeNode) {
	var spaceWidth = 5;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
}

//数据过滤
function ajaxDataFilter(treeId, parentNode, responseData) {
	if (responseData) {
		var data = null;
    	for(var i =0; i < responseData.length; i++) {
    		data = responseData[i];
    		data.name = filterNodeName(responseData[i].name);
        	if(data.font != undefined){
        		data.font = JSON.parse(data.font);
        	}
        	
        	if(data.isParent && data.childrenCount != undefined){
        		data.name = data.name + " [" + data.childrenCount + "]";
        	}
      	}
  	}
	
	return responseData;
};

//显示最长字符串，多出的用省略号
function filterNodeName(nodeName,maxWidth){
	if(maxWidth == undefined){
		maxWidth = 12;
	}
	
	if(nodeName.length > maxWidth){
		return nodeName.substring(0, maxWidth) + "...";
	}
	else{
		return nodeName;
	}
}