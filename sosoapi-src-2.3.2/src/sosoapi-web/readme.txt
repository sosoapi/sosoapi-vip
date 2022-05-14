开发规范
1、ajax增删改查
  /json/add.htm,/json/update.htm,/json/del.htm,/json/info.htm,/json/list.htm
  
2、定时任务服务以Job结尾

3、jquery submit表单时，如果有通过bootstrapValidator进行校验，则需要将提交表单type设置为"button",
      通过bootstrapValidator的defaultSubmit进行提交，无法通过jquery.submit进行提交。
      
--args --disable-web-security --user-data-dir

tomcat 开启gzip
<Connector port="9000" protocol="HTTP/1.1" connectionTimeout="20000"
        redirectPort="9011" URIEncoding="UTF-8" maxThreads="500" acceptCount="500" maxProcessors="200" minProcessors="50" enableLookups="false"
        compression="on" compressionMinSize="2048" noCompressionUserAgents="gozilla, traviata"
                compressableMimeType="text/html,text/xml,text/plain,text/css,
                         text/javascript,text/json,application/x-javascript,
                                  application/javascript,application/json" />
                                  
4、验证码乱码的问题是因为centos不支持的，把windows下的Times New Roman这些字体放到centos上就可以了；
