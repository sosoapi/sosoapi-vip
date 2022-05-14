1、html-templ-basic.html是swagger2markup模板
2、html-tmpl.html为html模板，从html-templ-basic.html改造
3、doc-tmpl.html为doc模板，从html-tmpl.html改造
   doc中的宽度必须设置为px,不能以百分比，否则宽度按内容展示，会不一致。
   a.设置表格统一宽度为800px,
   table.tableblock{
	   	max-width:100%;
	   	border-collapse:separate;
	   	table-layout:fixed;
	   	width:800px;
   }
   
   b.设置页面内容左侧边距，单位为px
   #content{
   		margin-top:1.25em;
   		margin-left:30px;
   }
   
   c.新增doc-title样式
4、导出pdf可以使用wkhtmltopdf
   https://wkhtmltopdf.org/downloads.html
   
5、freemarker语法
   http://blog.csdn.net/fhx007/article/details/7902040/
   
6、wkhtmltopdf中内部链接失效原因为跳转链接为空，如
   	<div class="cust-paragraph">
		<a name="_inter_221_resp_schema_001" href="#_inter_221_resp_schema_001"></a>
		[<span class="ref-title">001</span>]
	</div>
   	需更改为如下，保证a标签内部不能为空
   <div class="cust-paragraph">
		<a name="_inter_221_resp_schema_001" href="#_inter_221_resp_schema_001">
			[<span class="ref-title">001</span>]
		</a>
	</div>
   