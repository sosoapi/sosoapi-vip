<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/nestable/ext/css/nestable.ext.css" rel="stylesheet">
	<style type="text/css">
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="blank">
	<div class="content animate-panel">
		<div class="row">
		    <div class="col-lg-offset-3 col-lg-6">
		        <div id="nestable-menu">
		            <button type="button" data-action="expand-all" class="btn btn-default btn-sm">Expand All</button>
		            <button type="button" data-action="collapse-all" class="btn btn-default btn-sm">Collapse All</button>
		        </div>
		    </div>
		</div>

		<div class="row">
		    <div class="col-lg-offset-3 col-lg-6">
		        <div class="hpanel">
		            <div class="panel-heading">
		                <div class="panel-tools">
		                    <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a>
		                </div>
		                Nestable basic list
		            </div>
		            <div class="panel-body">
		
		                <p  class="m-b-lg">
		                    <strong>Nestable</strong> is an interactive hierarchical list. You can drag and drop to rearrange the order. It works well on touch-screens.
		                </p>
		
		                <div class="dd" id="nestable">
		                    <ol class="dd-list">
		                        <li class="dd-item dd-item-drag" data-id="1">
		                            <div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">1 - Lorem ipsum</div>
		                        </li>
		                        <li class="dd-item dd-item-drag" data-id="2">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">2 - Dolor sit</div>
		                            <ol class="dd-list">
		                                <li class="dd-item dd-item-drag" data-id="3">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">3 - Adipiscing elit</div>
		                                </li>
		                                <li class="dd-item dd-item-drag" data-id="4">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">4 - Nonummy nibh</div>
		                                </li>
		                            </ol>
		                        </li>
		                        <li class="dd-item dd-item-drag" data-id="5">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">5 - Consectetuer</div>
		                            <ol class="dd-list">
		                                <li class="dd-item dd-item-drag" data-id="6">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">6 - Aliquam erat</div>
		                                </li>
		                                <li class="dd-item dd-item-drag" data-id="7">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">7 - Veniam quis</div>
		                                </li>
		                            </ol>
		                        </li>
		                        <li class="dd-item dd-item-drag" data-id="8">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">8 - Tation ullamcorper</div>
		                        </li>
		                        <li class="dd-item dd-item-drag" data-id="9">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">9 - Ea commodo</div>
		                        </li>
		                    </ol>
		                </div>
		                <div class="m-t-md">
		                    <h5>Serialised Output</h5>
		                </div>
		                <textarea id="nestable-output" class="form-control"></textarea>
		
		            </div>
		        </div>
		    </div>
		</div>
	</div>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/nestable/ext/js/jquery.nestable.ext.js"></script>
    <script type="text/javascript">
    $(function () {
        var updateOutput = function (e) {
            var list = e.length ? e : $(e.target),
                    output = list.data('output');
            if (window.JSON) {
                output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
            } else {
                output.val('JSON browser support required for this demo.');
            }
        };
        // activate Nestable for list 1
        $('#nestable').nestable({
            group: 1
        }).on('change', updateOutput);

        // output initial serialised data
        updateOutput($('#nestable').data('output', $('#nestable-output')));

        $('#nestable-menu').on('click', function (e) {
            var target = $(e.target),
                    action = target.data('action');
            if (action === 'expand-all') {
                $('.dd').nestable('expandAll');
            }
            if (action === 'collapse-all') {
                $('.dd').nestable('collapseAll');
            }
        });

    });
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
