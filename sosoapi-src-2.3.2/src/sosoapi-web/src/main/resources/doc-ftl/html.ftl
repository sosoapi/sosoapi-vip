<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="generator" content="SosoApi">
<title>${apiDoc.title} ${apiDoc.version}</title>
<style>
article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block}
audio,canvas,video{display:inline-block}
audio:not([controls]){display:none;height:0}
[hidden],template{display:none}
script{display:none!important}
html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}
body{margin:0}
a{background:transparent}
a:focus{outline:thin dotted}
a:active,a:hover{outline:0}
h1{font-size:2em;margin:.67em 0}
abbr[title]{border-bottom:1px dotted}
b,strong{font-weight:bold}
dfn{font-style:italic}
hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0}
mark{background:#ff0;color:#000}
code,kbd,pre,samp{font-family:monospace;font-size:1em}
pre{white-space:pre-wrap}
q{quotes:"\201C" "\201D" "\2018" "\2019"}
small{font-size:80%}
sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}
sup{top:-.5em}
sub{bottom:-.25em}
img{border:0}
svg:not(:root){overflow:hidden}
figure{margin:0}
fieldset{border:1px solid silver;margin:0 2px;padding:.35em .625em .75em}
legend{border:0;padding:0}
button,input,select,textarea{font-family:inherit;font-size:100%;margin:0}
button,input{line-height:normal}
button,select{text-transform:none}
button,html input[type="button"],input[type="reset"],input[type="submit"]{-webkit-appearance:button;cursor:pointer}
button[disabled],html input[disabled]{cursor:default}
input[type="checkbox"],input[type="radio"]{box-sizing:border-box;padding:0}
input[type="search"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box}
input[type="search"]::-webkit-search-cancel-button,input[type="search"]::-webkit-search-decoration{-webkit-appearance:none}
button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0}
textarea{overflow:auto;vertical-align:top}
table{border-collapse:collapse;border-spacing:0}
*,*:before,*:after{-moz-box-sizing:border-box;-webkit-box-sizing:border-box;box-sizing:border-box}
html,body{font-size:100%}
body{background:#fff;color:rgba(0,0,0,.8);padding:0;margin:0;font-family:"Noto Serif","DejaVu Serif",serif;font-weight:400;font-style:normal;line-height:1;position:relative;cursor:auto}
a:hover{cursor:pointer}
img,object,embed{max-width:100%;height:auto}
object,embed{height:100%}
img{-ms-interpolation-mode:bicubic}
.left{float:left!important}
.right{float:right!important}
.text-left{text-align:left!important}
.text-right{text-align:right!important}
.text-center{text-align:center!important}
.text-justify{text-align:justify!important}
.hide{display:none}
body{-webkit-font-smoothing:antialiased}
img,object,svg{display:inline-block;vertical-align:middle}
textarea{height:auto;min-height:50px}
select{width:100%}
.center{margin-left:auto;margin-right:auto}
.spread{width:100%}
p.lead,.paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{font-size:1.21875em;line-height:1.6}
.subheader,.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{line-height:1.45;color:#7a2518;font-weight:400;margin-top:0;margin-bottom:.25em}
div,dl,dt,dd,ul,ol,li,h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6,pre,form,p,blockquote,th,td{margin:0;padding:0;direction:ltr}
a{color:#2156a5;text-decoration:underline;line-height:inherit}
a:hover,a:focus{color:#1d4b8f}
a img{border:none}
p{font-family:inherit;font-weight:400;font-size:1em;line-height:1.6;margin-bottom:1.25em;text-rendering:optimizeLegibility}
p aside{font-size:.875em;line-height:1.35;font-style:italic}
h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{font-family:"Open Sans","DejaVu Sans",sans-serif;font-weight:300;font-style:normal;color:#ba3925;text-rendering:optimizeLegibility;margin-top:1em;margin-bottom:.5em;line-height:1.0125em}
h1 small,h2 small,h3 small,#toctitle small,.sidebarblock>.content>.title small,h4 small,h5 small,h6 small{font-size:60%;color:#e99b8f;line-height:0}
h1{font-size:2.125em}
h2{font-size:1.6875em}
h3,#toctitle,.sidebarblock>.content>.title{font-size:1.375em}
h4,h5{font-size:1.125em}
h6{font-size:1em}
hr{border:solid #ddddd8;border-width:1px 0 0;clear:both;margin:1.25em 0 1.1875em;height:0}
em,i{font-style:italic;line-height:inherit}
strong,b{font-weight:bold;line-height:inherit}
small{font-size:60%;line-height:inherit}
code{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;font-weight:400;color:rgba(0,0,0,.9)}
ul,ol,dl{font-size:1em;line-height:1.6;margin-bottom:1.25em;list-style-position:outside;font-family:inherit}
ul,ol,ul.no-bullet,ol.no-bullet{margin-left:1.5em}
ul li ul,ul li ol{margin-left:1.25em;margin-bottom:0;font-size:1em}
ul.square li ul,ul.circle li ul,ul.disc li ul{list-style:inherit}
ul.square{list-style-type:square}
ul.circle{list-style-type:circle}
ul.disc{list-style-type:disc}
ul.no-bullet{list-style:none}
ol li ul,ol li ol{margin-left:1.25em;margin-bottom:0}
dl dt{margin-bottom:.3125em;font-weight:bold}
dl dd{margin-bottom:1.25em}
abbr,acronym{text-transform:uppercase;font-size:90%;color:rgba(0,0,0,.8);border-bottom:1px dotted #ddd;cursor:help}
abbr{text-transform:none}
blockquote{margin:0 0 1.25em;padding:.5625em 1.25em 0 1.1875em;border-left:1px solid #ddd}
blockquote cite{display:block;font-size:.9375em;color:rgba(0,0,0,.6)}
blockquote cite:before{content:"\2014 \0020"}
blockquote cite a,blockquote cite a:visited{color:rgba(0,0,0,.6)}
blockquote,blockquote p{line-height:1.6;color:rgba(0,0,0,.85)}
@media only screen and (min-width:768px){h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2}
h1{font-size:2.75em}
h2{font-size:2.3125em}
h3,#toctitle,.sidebarblock>.content>.title{font-size:1.6875em}
h4{font-size:1.4375em}}
table{background:#fff;margin-bottom:1.25em;border:solid 1px #dedede}
table thead,table tfoot{background:#f7f8f7;font-weight:bold}
table thead tr th,table thead tr td,table tfoot tr th,table tfoot tr td{padding:.5em .625em .625em;font-size:inherit;color:rgba(0,0,0,.8);text-align:left}
table tr th,table tr td{padding:.5625em .625em;font-size:inherit;color:rgba(0,0,0,.8)}
table tr.even,table tr.alt,table tr:nth-of-type(even){background:#f8f8f7}
table thead tr th,table tfoot tr th,table tbody tr td,table tr td,table tfoot tr td{display:table-cell;line-height:1.6}
body{tab-size:4}
h1,h2,h3,#toctitle,.sidebarblock>.content>.title,h4,h5,h6{line-height:1.2;word-spacing:-.05em}
h1 strong,h2 strong,h3 strong,#toctitle strong,.sidebarblock>.content>.title strong,h4 strong,h5 strong,h6 strong{font-weight:400}
.clearfix:before,.clearfix:after,.float-group:before,.float-group:after{content:" ";display:table}
.clearfix:after,.float-group:after{clear:both}
*:not(pre)>code{font-size:.9375em;font-style:normal!important;letter-spacing:0;padding:.1em .5ex;word-spacing:-.15em;background-color:#f7f7f8;-webkit-border-radius:4px;border-radius:4px;line-height:1.45;text-rendering:optimizeSpeed}
pre,pre>code{line-height:1.45;color:rgba(0,0,0,.9);font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;font-weight:400;text-rendering:optimizeSpeed}
.keyseq{color:rgba(51,51,51,.8)}
kbd{font-family:"Droid Sans Mono","DejaVu Sans Mono",monospace;display:inline-block;color:rgba(0,0,0,.8);font-size:.65em;line-height:1.45;background-color:#f7f7f7;border:1px solid #ccc;-webkit-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em white inset;box-shadow:0 1px 0 rgba(0,0,0,.2),0 0 0 .1em #fff inset;margin:0 .15em;padding:.2em .5em;vertical-align:middle;position:relative;top:-.1em;white-space:nowrap}
.keyseq kbd:first-child{margin-left:0}
.keyseq kbd:last-child{margin-right:0}
.menuseq,.menu{color:rgba(0,0,0,.8)}
b.button:before,b.button:after{position:relative;top:-1px;font-weight:400}
b.button:before{content:"[";padding:0 3px 0 2px}
b.button:after{content:"]";padding:0 2px 0 3px}
p a>code:hover{color:rgba(0,0,0,.9)}
#header,#content,#footnotes,#footer{width:100%;margin-left:auto;margin-right:auto;margin-top:0;margin-bottom:0;max-width:62.5em;*zoom:1;position:relative;padding-left:.9375em;padding-right:.9375em}
#header:before,#header:after,#content:before,#content:after,#footnotes:before,#footnotes:after,#footer:before,#footer:after{content:" ";display:table}
#header:after,#content:after,#footnotes:after,#footer:after{clear:both}
#content{margin-top:1.25em}
#content:before{content:none}
#header>h1:first-child{color:rgba(0,0,0,.85);margin-top:2.25rem;margin-bottom:0}
#header>h1:first-child+#toc{margin-top:8px;border-top:1px solid #ddddd8}
#header>h1:only-child,body.toc2 #header>h1:nth-last-child(2){border-bottom:1px solid #ddddd8;padding-bottom:8px}
#header .details{border-bottom:1px solid #ddddd8;line-height:1.45;padding-top:.25em;padding-bottom:.25em;padding-left:.25em;color:rgba(0,0,0,.6);display:-ms-flexbox;display:-webkit-flex;display:flex;-ms-flex-flow:row wrap;-webkit-flex-flow:row wrap;flex-flow:row wrap}
#header .details span:first-child{margin-left:-.125em}
#header .details span.email a{color:rgba(0,0,0,.85)}
#header .details br{display:none}
#header .details br+span:before{content:"\00a0\2013\00a0"}
#header .details br+span.author:before{content:"\00a0\22c5\00a0";color:rgba(0,0,0,.85)}
#header .details br+span#revremark:before{content:"\00a0|\00a0"}
#header #revnumber{text-transform:capitalize}
#header #revnumber:after{content:"\00a0"}
#content>h1:first-child:not([class]){color:rgba(0,0,0,.85);border-bottom:1px solid #ddddd8;padding-bottom:8px;margin-top:0;padding-top:1rem;margin-bottom:1.25rem}
#toc{border-bottom:1px solid #efefed;padding-bottom:.5em}
#toc>ul{margin-left:.125em}
#toc ul.sectlevel0>li>a{font-style:italic}
#toc ul.sectlevel0 ul.sectlevel1{margin:.5em 0}
#toc ul{font-family:"Open Sans","DejaVu Sans",sans-serif;list-style-type:none}
#toc li{line-height:1.3334;margin-top:.3334em}
#toc a{text-decoration:none}
#toc a:active{text-decoration:underline}
#toctitle{color:#7a2518;font-size:1.2em}
@media only screen and (min-width:768px){#toctitle{font-size:1.375em}
body.toc2{padding-left:15em;padding-right:0}
#toc.toc2{margin-top:0!important;background-color:#f8f8f7;position:fixed;width:15em;left:0;top:0;border-right:1px solid #efefed;border-top-width:0!important;border-bottom-width:0!important;z-index:1000;padding:1.25em 1em;height:100%;overflow:auto}
#toc.toc2 #toctitle{margin-top:0;margin-bottom:.8rem;font-size:1.2em}
#toc.toc2>ul{font-size:.9em;margin-bottom:0}
/* #toc.toc2 ul ul{margin-left:0;padding-left:1em} */
#toc.toc2 ul ul{margin-left:0;padding-left:0}
#toc.toc2 ul.sectlevel0 ul.sectlevel1{padding-left:0;margin-top:.5em;margin-bottom:.5em}
body.toc2.toc-right{padding-left:0;padding-right:15em}
body.toc2.toc-right #toc.toc2{border-right-width:0;border-left:1px solid #efefed;left:auto;right:0}}
@media only screen and (min-width:1280px){body.toc2{padding-left:20em;padding-right:0}
#toc.toc2{width:20em}
#toc.toc2 #toctitle{font-size:1.375em}
#toc.toc2>ul{font-size:.95em}
/* #toc.toc2 ul ul{padding-left:1.25em} */
#toc.toc2 ul ul{padding-left:0}
body.toc2.toc-right{padding-left:0;padding-right:20em}}
#content #toc{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}
#content #toc>:first-child{margin-top:0}
#content #toc>:last-child{margin-bottom:0}
#footer{max-width:100%;background-color:rgba(0,0,0,.8);padding:1.25em}
#footer-text{color:rgba(255,255,255,.8);line-height:1.44}
.sect1{padding-bottom:.625em}
@media only screen and (min-width:768px){.sect1{padding-bottom:1.25em}}
.sect1+.sect1{border-top:1px solid #efefed}
#content h1>a.anchor,h2>a.anchor,h3>a.anchor,#toctitle>a.anchor,.sidebarblock>.content>.title>a.anchor,h4>a.anchor,h5>a.anchor,h6>a.anchor{position:absolute;z-index:1001;width:1.5ex;margin-left:-1.5ex;display:block;text-decoration:none!important;visibility:hidden;text-align:center;font-weight:400}
#content h1>a.anchor:before,h2>a.anchor:before,h3>a.anchor:before,#toctitle>a.anchor:before,.sidebarblock>.content>.title>a.anchor:before,h4>a.anchor:before,h5>a.anchor:before,h6>a.anchor:before{content:"\00A7";font-size:.85em;display:block;padding-top:.1em}
#content h1:hover>a.anchor,#content h1>a.anchor:hover,h2:hover>a.anchor,h2>a.anchor:hover,h3:hover>a.anchor,#toctitle:hover>a.anchor,.sidebarblock>.content>.title:hover>a.anchor,h3>a.anchor:hover,#toctitle>a.anchor:hover,.sidebarblock>.content>.title>a.anchor:hover,h4:hover>a.anchor,h4>a.anchor:hover,h5:hover>a.anchor,h5>a.anchor:hover,h6:hover>a.anchor,h6>a.anchor:hover{visibility:visible}
#content h1>a.link,h2>a.link,h3>a.link,#toctitle>a.link,.sidebarblock>.content>.title>a.link,h4>a.link,h5>a.link,h6>a.link{color:#ba3925;text-decoration:none}
#content h1>a.link:hover,h2>a.link:hover,h3>a.link:hover,#toctitle>a.link:hover,.sidebarblock>.content>.title>a.link:hover,h4>a.link:hover,h5>a.link:hover,h6>a.link:hover{color:#a53221}
.audioblock,.imageblock,.literalblock,.listingblock,.stemblock,.videoblock{margin-bottom:1.25em}
.admonitionblock td.content>.title,.audioblock>.title,.exampleblock>.title,.imageblock>.title,.listingblock>.title,.literalblock>.title,.stemblock>.title,.openblock>.title,.paragraph>.title,.quoteblock>.title,table.tableblock>.title,.verseblock>.title,.videoblock>.title,.dlist>.title,.olist>.title,.ulist>.title,.qlist>.title,.hdlist>.title{text-rendering:optimizeLegibility;text-align:left;font-family:"Noto Serif","DejaVu Serif",serif;font-size:1rem;font-style:italic}
table.tableblock>caption.title{white-space:nowrap;overflow:visible;max-width:0}
.paragraph.lead>p,#preamble>.sectionbody>.paragraph:first-of-type p{color:rgba(0,0,0,.85)}
table.tableblock #preamble>.sectionbody>.paragraph:first-of-type p{font-size:inherit}
.admonitionblock>table{border-collapse:separate;border:0;background:none;width:100%}
.admonitionblock>table td.icon{text-align:center;width:80px}
.admonitionblock>table td.icon img{max-width:none}
.admonitionblock>table td.icon .title{font-weight:bold;font-family:"Open Sans","DejaVu Sans",sans-serif;text-transform:uppercase}
.admonitionblock>table td.content{padding-left:1.125em;padding-right:1.25em;border-left:1px solid #ddddd8;color:rgba(0,0,0,.6)}
.admonitionblock>table td.content>:last-child>:last-child{margin-bottom:0}
.exampleblock>.content{border-style:solid;border-width:1px;border-color:#e6e6e6;margin-bottom:1.25em;padding:1.25em;background:#fff;-webkit-border-radius:4px;border-radius:4px}
.exampleblock>.content>:first-child{margin-top:0}
.exampleblock>.content>:last-child{margin-bottom:0}
.sidebarblock{border-style:solid;border-width:1px;border-color:#e0e0dc;margin-bottom:1.25em;padding:1.25em;background:#f8f8f7;-webkit-border-radius:4px;border-radius:4px}
.sidebarblock>:first-child{margin-top:0}
.sidebarblock>:last-child{margin-bottom:0}
.sidebarblock>.content>.title{color:#7a2518;margin-top:0;text-align:center}
.exampleblock>.content>:last-child>:last-child,.exampleblock>.content .olist>ol>li:last-child>:last-child,.exampleblock>.content .ulist>ul>li:last-child>:last-child,.exampleblock>.content .qlist>ol>li:last-child>:last-child,.sidebarblock>.content>:last-child>:last-child,.sidebarblock>.content .olist>ol>li:last-child>:last-child,.sidebarblock>.content .ulist>ul>li:last-child>:last-child,.sidebarblock>.content .qlist>ol>li:last-child>:last-child{margin-bottom:0}
.literalblock pre,.listingblock pre:not(.highlight),.listingblock pre[class="highlight"],.listingblock pre[class^="highlight "],.listingblock pre.CodeRay,.listingblock pre.prettyprint{background:#f7f7f8}
.sidebarblock .literalblock pre,.sidebarblock .listingblock pre:not(.highlight),.sidebarblock .listingblock pre[class="highlight"],.sidebarblock .listingblock pre[class^="highlight "],.sidebarblock .listingblock pre.CodeRay,.sidebarblock .listingblock pre.prettyprint{background:#f2f1f1}
.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{-webkit-border-radius:4px;border-radius:4px;word-wrap:break-word;padding:1em;font-size:.8125em}
.literalblock pre.nowrap,.literalblock pre[class].nowrap,.listingblock pre.nowrap,.listingblock pre[class].nowrap{overflow-x:auto;white-space:pre;word-wrap:normal}
@media only screen and (min-width:768px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:.90625em}}
@media only screen and (min-width:1280px){.literalblock pre,.literalblock pre[class],.listingblock pre,.listingblock pre[class]{font-size:1em}}
.literalblock.output pre{color:#f7f7f8;background-color:rgba(0,0,0,.9)}
.listingblock pre.highlightjs{padding:0}
.listingblock pre.highlightjs>code{padding:1em;-webkit-border-radius:4px;border-radius:4px}
.listingblock pre.prettyprint{border-width:0}
.listingblock>.content{position:relative}
.listingblock code[data-lang]:before{display:none;content:attr(data-lang);position:absolute;font-size:.75em;top:.425rem;right:.5rem;line-height:1;text-transform:uppercase;color:#999}
.listingblock:hover code[data-lang]:before{display:block}
.listingblock.terminal pre .command:before{content:attr(data-prompt);padding-right:.5em;color:#999}
.listingblock.terminal pre .command:not([data-prompt]):before{content:"$"}
table.pyhltable{border-collapse:separate;border:0;margin-bottom:0;background:none}
table.pyhltable td{vertical-align:top;padding-top:0;padding-bottom:0;line-height:1.45}
table.pyhltable td.code{padding-left:.75em;padding-right:0}
pre.pygments .lineno,table.pyhltable td:not(.code){color:#999;padding-left:0;padding-right:.5em;border-right:1px solid #ddddd8}
pre.pygments .lineno{display:inline-block;margin-right:.25em}
table.pyhltable .linenodiv{background:none!important;padding-right:0!important}
.quoteblock{margin:0 1em 1.25em 1.5em;display:table}
.quoteblock>.title{margin-left:-1.5em;margin-bottom:.75em}
.quoteblock blockquote,.quoteblock blockquote p{color:rgba(0,0,0,.85);font-size:1.15rem;line-height:1.75;word-spacing:.1em;letter-spacing:0;font-style:italic;text-align:justify}
.quoteblock blockquote{margin:0;padding:0;border:0}
.quoteblock blockquote:before{content:"\201c";float:left;font-size:2.75em;font-weight:bold;line-height:.6em;margin-left:-.6em;color:#7a2518;text-shadow:0 1px 2px rgba(0,0,0,.1)}
.quoteblock blockquote>.paragraph:last-child p{margin-bottom:0}
.quoteblock .attribution{margin-top:.5em;margin-right:.5ex;text-align:right}
.quoteblock .quoteblock{margin-left:0;margin-right:0;padding:.5em 0;border-left:3px solid rgba(0,0,0,.6)}
.quoteblock .quoteblock blockquote{padding:0 0 0 .75em}
.quoteblock .quoteblock blockquote:before{display:none}
.verseblock{margin:0 1em 1.25em 1em}
.verseblock pre{font-family:"Open Sans","DejaVu Sans",sans;font-size:1.15rem;color:rgba(0,0,0,.85);font-weight:300;text-rendering:optimizeLegibility}
.verseblock pre strong{font-weight:400}
.verseblock .attribution{margin-top:1.25rem;margin-left:.5ex}
.quoteblock .attribution,.verseblock .attribution{font-size:.9375em;line-height:1.45;font-style:italic}
.quoteblock .attribution br,.verseblock .attribution br{display:none}
.quoteblock .attribution cite,.verseblock .attribution cite{display:block;letter-spacing:-.025em;color:rgba(0,0,0,.6)}
.quoteblock.abstract{margin:0 0 1.25em 0;display:block}
.quoteblock.abstract blockquote,.quoteblock.abstract blockquote p{text-align:left;word-spacing:0}
.quoteblock.abstract blockquote:before,.quoteblock.abstract blockquote p:first-of-type:before{display:none}
table.tableblock{max-width:100%;border-collapse:separate;table-layout:fixed;}
table.tableblock td>.paragraph:last-child p>p:last-child,table.tableblock th>p:last-child,table.tableblock td>p:last-child{margin-bottom:0}
table.tableblock,th.tableblock,td.tableblock{border:0 solid #dedede;word-wrap: break-word;word-break: break-all;}
table.grid-all th.tableblock,table.grid-all td.tableblock{border-width:0 1px 1px 0}
table.grid-all tfoot>tr>th.tableblock,table.grid-all tfoot>tr>td.tableblock{border-width:1px 1px 0 0}
table.grid-cols th.tableblock,table.grid-cols td.tableblock{border-width:0 1px 0 0}
table.grid-all *>tr>.tableblock:last-child,table.grid-cols *>tr>.tableblock:last-child{border-right-width:0}
table.grid-rows th.tableblock,table.grid-rows td.tableblock{border-width:0 0 1px 0}
table.grid-all tbody>tr:last-child>th.tableblock,table.grid-all tbody>tr:last-child>td.tableblock,table.grid-all thead:last-child>tr>th.tableblock,table.grid-rows tbody>tr:last-child>th.tableblock,table.grid-rows tbody>tr:last-child>td.tableblock,table.grid-rows thead:last-child>tr>th.tableblock{border-bottom-width:0}
table.grid-rows tfoot>tr>th.tableblock,table.grid-rows tfoot>tr>td.tableblock{border-width:1px 0 0 0}
table.frame-all{border-width:1px}
table.frame-sides{border-width:0 1px}
table.frame-topbot{border-width:1px 0}
th.halign-left,td.halign-left{text-align:left}
th.halign-right,td.halign-right{text-align:right}
th.halign-center,td.halign-center{text-align:center}
th.valign-top,td.valign-top{vertical-align:top}
th.valign-bottom,td.valign-bottom{vertical-align:bottom}
th.valign-middle,td.valign-middle{vertical-align:middle}
table thead th,table tfoot th{font-weight:bold}
tbody tr th{display:table-cell;line-height:1.6;background:#f7f8f7}
tbody tr th,tbody tr th p,tfoot tr th,tfoot tr th p{color:rgba(0,0,0,.8);font-weight:bold}
p.tableblock>code:only-child{background:none;padding:0}
p.tableblock{font-size:1em}
td>div.verse{white-space:pre}
ol{margin-left:1.75em}
ul li ol{margin-left:1.5em}
dl dd{margin-left:1.125em}
dl dd:last-child,dl dd:last-child>:last-child{margin-bottom:0}
ol>li p,ul>li p,ul dd,ol dd,.olist .olist,.ulist .ulist,.ulist .olist,.olist .ulist{margin-bottom:.625em}
ul.unstyled,ol.unnumbered,ul.checklist,ul.none{list-style-type:none}
ul.unstyled,ol.unnumbered,ul.checklist{margin-left:.625em}
ul.checklist li>p:first-child>.fa-square-o:first-child,ul.checklist li>p:first-child>.fa-check-square-o:first-child{width:1em;font-size:.85em}
ul.checklist li>p:first-child>input[type="checkbox"]:first-child{width:1em;position:relative;top:1px}
ul.inline{margin:0 auto .625em auto;margin-left:-1.375em;margin-right:0;padding:0;list-style:none;overflow:hidden}
ul.inline>li{list-style:none;float:left;margin-left:1.375em;display:block}
ul.inline>li>*{display:block}
.unstyled dl dt{font-weight:400;font-style:normal}
ol.arabic{list-style-type:decimal}
ol.decimal{list-style-type:decimal-leading-zero}
ol.loweralpha{list-style-type:lower-alpha}
ol.upperalpha{list-style-type:upper-alpha}
ol.lowerroman{list-style-type:lower-roman}
ol.upperroman{list-style-type:upper-roman}
ol.lowergreek{list-style-type:lower-greek}
.hdlist>table,.colist>table{border:0;background:none}
.hdlist>table>tbody>tr,.colist>table>tbody>tr{background:none}
td.hdlist1,td.hdlist2{vertical-align:top;padding:0 .625em}
td.hdlist1{font-weight:bold;padding-bottom:1.25em}
.literalblock+.colist,.listingblock+.colist{margin-top:-.5em}
.colist>table tr>td:first-of-type{padding:0 .75em;line-height:1}
.colist>table tr>td:last-of-type{padding:.25em 0}
.thumb,.th{line-height:0;display:inline-block;border:solid 4px #fff;-webkit-box-shadow:0 0 0 1px #ddd;box-shadow:0 0 0 1px #ddd}
.imageblock.left,.imageblock[style*="float: left"]{margin:.25em .625em 1.25em 0}
.imageblock.right,.imageblock[style*="float: right"]{margin:.25em 0 1.25em .625em}
.imageblock>.title{margin-bottom:0}
.imageblock.thumb,.imageblock.th{border-width:6px}
.imageblock.thumb>.title,.imageblock.th>.title{padding:0 .125em}
.image.left,.image.right{margin-top:.25em;margin-bottom:.25em;display:inline-block;line-height:0}
.image.left{margin-right:.625em}
.image.right{margin-left:.625em}
a.image{text-decoration:none;display:inline-block}
a.image object{pointer-events:none}
sup.footnote,sup.footnoteref{font-size:.875em;position:static;vertical-align:super}
sup.footnote a,sup.footnoteref a{text-decoration:none}
sup.footnote a:active,sup.footnoteref a:active{text-decoration:underline}
#footnotes{padding-top:.75em;padding-bottom:.75em;margin-bottom:.625em}
#footnotes hr{width:20%;min-width:6.25em;margin:-.25em 0 .75em 0;border-width:1px 0 0 0}
#footnotes .footnote{padding:0 .375em 0 .225em;line-height:1.3334;font-size:.875em;margin-left:1.2em;text-indent:-1.05em;margin-bottom:.2em}
#footnotes .footnote a:first-of-type{font-weight:bold;text-decoration:none}
#footnotes .footnote:last-of-type{margin-bottom:0}
#content #footnotes{margin-top:-.625em;margin-bottom:0;padding:.75em 0}
.gist .file-data>table{border:0;background:#fff;width:100%;margin-bottom:0}
.gist .file-data>table td.line-data{width:99%}
div.unbreakable{page-break-inside:avoid}
.big{font-size:larger}
.small{font-size:smaller}
.underline{text-decoration:underline}
.overline{text-decoration:overline}
.line-through{text-decoration:line-through}
.aqua{color:#00bfbf}
.aqua-background{background-color:#00fafa}
.black{color:#000}
.black-background{background-color:#000}
.blue{color:#0000bf}
.blue-background{background-color:#0000fa}
.fuchsia{color:#bf00bf}
.fuchsia-background{background-color:#fa00fa}
.gray{color:#606060}
.gray-background{background-color:#7d7d7d}
.green{color:#006000}
.green-background{background-color:#007d00}
.lime{color:#00bf00}
.lime-background{background-color:#00fa00}
.maroon{color:#600000}
.maroon-background{background-color:#7d0000}
.navy{color:#000060}
.navy-background{background-color:#00007d}
.olive{color:#606000}
.olive-background{background-color:#7d7d00}
.purple{color:#600060}
.purple-background{background-color:#7d007d}
.red{color:#bf0000}
.red-background{background-color:#fa0000}
.silver{color:#909090}
.silver-background{background-color:#bcbcbc}
.teal{color:#006060}
.teal-background{background-color:#007d7d}
.white{color:#bfbfbf}
.white-background{background-color:#fafafa}
.yellow{color:#bfbf00}
.yellow-background{background-color:#fafa00}
span.icon>.fa{cursor:default}
.admonitionblock td.icon [class^="fa icon-"]{font-size:2.5em;text-shadow:1px 1px 2px rgba(0,0,0,.5);cursor:default}
.admonitionblock td.icon .icon-note:before{content:"\f05a";color:#19407c}
.admonitionblock td.icon .icon-tip:before{content:"\f0eb";text-shadow:1px 1px 2px rgba(155,155,0,.8);color:#111}
.admonitionblock td.icon .icon-warning:before{content:"\f071";color:#bf6900}
.admonitionblock td.icon .icon-caution:before{content:"\f06d";color:#bf3400}
.admonitionblock td.icon .icon-important:before{content:"\f06a";color:#bf0000}
.conum[data-value]{display:inline-block;color:#fff!important;background-color:rgba(0,0,0,.8);-webkit-border-radius:100px;border-radius:100px;text-align:center;font-size:.75em;width:1.67em;height:1.67em;line-height:1.67em;font-family:"Open Sans","DejaVu Sans",sans-serif;font-style:normal;font-weight:bold}
.conum[data-value] *{color:#fff!important}
.conum[data-value]+b{display:none}
.conum[data-value]:after{content:attr(data-value)}
pre .conum[data-value]{position:relative;top:-.125em}
b.conum *{color:inherit!important}
.conum:not([data-value]):empty{display:none}
dt,th.tableblock,td.content,div.footnote{text-rendering:optimizeLegibility}
h1,h2,p,td.content,span.alt{letter-spacing:-.01em}
p strong,td.content strong,div.footnote strong{letter-spacing:-.005em}
p,blockquote,dt,td.content,span.alt{font-size:1.0625rem}
p{margin-bottom:1.25rem}
.sidebarblock p,.sidebarblock dt,.sidebarblock td.content,p.tableblock{font-size:1em}
.exampleblock>.content{background-color:#fffef7;border-color:#e0e0dc;-webkit-box-shadow:0 1px 4px #e0e0dc;box-shadow:0 1px 4px #e0e0dc}
.print-only{display:none!important}
@media print{@page{margin:1.25cm .75cm}
*{-webkit-box-shadow:none!important;box-shadow:none!important;text-shadow:none!important}
a{color:inherit!important;text-decoration:underline!important}
a.bare,a[href^="#"],a[href^="mailto:"]{text-decoration:none!important}
a[href^="http:"]:not(.bare):after,a[href^="https:"]:not(.bare):after{content:"(" attr(href) ")";display:inline-block;font-size:.875em;padding-left:.25em}
abbr[title]:after{content:" (" attr(title) ")"}
pre,blockquote,tr,img,object,svg{page-break-inside:avoid}
thead{display:table-header-group}
svg{max-width:100%}
p,blockquote,dt,td.content{font-size:1em;orphans:3;widows:3}
h2,h3,#toctitle,.sidebarblock>.content>.title{page-break-after:avoid}
#toc,.sidebarblock,.exampleblock>.content{background:none!important}
#toc{border-bottom:1px solid #ddddd8!important;padding-bottom:0!important}
.sect1{padding-bottom:0!important}
.sect1+.sect1{border:0!important}
#header>h1:first-child{margin-top:1.25rem}
body.book #header{text-align:center}
body.book #header>h1:first-child{border:0!important;margin:2.5em 0 1em 0}
body.book #header .details{border:0!important;display:block;padding:0!important}
body.book #header .details span:first-child{margin-left:0!important}
body.book #header .details br{display:block}
body.book #header .details br+span:before{content:none!important}
body.book #toc{border:0!important;text-align:left!important;padding:0!important;margin:0!important}
body.book #toc,body.book #preamble,body.book h1.sect0,body.book .sect1>h2{page-break-before:always}
.listingblock code[data-lang]:before{display:block}
#footer{background:none!important;padding:0 .9375em}
#footer-text{color:rgba(0,0,0,.6)!important;font-size:.9em}
.hide-on-print{display:none!important}
.print-only{display:block!important}
.hide-for-print{display:none!important}
.show-for-print{display:inherit!important}}

/* 自定义样式 */
.schema-content {background: #f7f7f8;padding: 1em;}
.ref-title {color: #ba3925;}
.ref-info {
  font-size: 1.125em;
  font-family: "Open Sans","DejaVu Sans",sans-serif;
  font-weight: 300;
  font-style: normal;
  color: #ba3925;
  text-rendering: optimizeLegibility;
  margin-top: 1.5em;
  margin-bottom: .5em;
}
.cust-paragraph {margin-top: .5em; margin-bottom: 1em;}

/* 左侧导航 */
#toc ul { background:url("data:image/gif;base64,R0lGODdhAQACAIAAAAAAAP///ywAAAAAAQACAAACAgwKADs=") repeat-y 5px 0px;}
#toc ul li { padding:5px 0 2px 15px; background:url("data:image/gif;base64,R0lGODlhKQAsAKIAAP///4CAgFVVVQAAAP///wAAAAAAAAAAACH5BAEHAAQALAAAAAApACwAAANvSLrc/izIOaG9LYDNQcCgpW0D94VoxJXbmabaIMve+44AW9sorrs8DK4DDIooFKNyyWw6n9CodEqtWq/YKDJJHZqq3ha48+3mZqUiNLybstXPN3grydqbgruekd/v+353gIFZg4RXhodVeYwEjVQJADs=") no-repeat 5px -32px; }
#toc ul li ul {display:none;}
#toc ul li em {
  cursor:pointer;
  display:inline-block;
  width:15px;
  float:left;
  height:15px;
  margin-left:-14px;
  background:url("data:image/gif;base64,R0lGODlhKQAsAKIAAP///4CAgFVVVQAAAP///wAAAAAAAAAAACH5BAEHAAQALAAAAAApACwAAANvSLrc/izIOaG9LYDNQcCgpW0D94VoxJXbmabaIMve+44AW9sorrs8DK4DDIooFKNyyWw6n9CodEqtWq/YKDJJHZqq3ha48+3mZqUiNLybstXPN3grydqbgruekd/v+353gIFZg4RXhodVeYwEjVQJADs=") no-repeat -32px 2px;
}
#toc ul li em.off { background-position: -17px -18px;}
#toc ul li#end { background-color:#FFF;}
#toc ul.off { display:block;}
</style>
</head>
<body class="book toc2 toc-left">
	<div id="header">
		<h1>${apiDoc.title} ${apiDoc.version}</h1>
		<div id="toc" class="toc2">
			<div id="toctitle">目录</div>
			<ul>
				<li>
					<em></em><a href="#_overview">1. 概述</a>
					<ul>
						<li>
							<a href="#_doc_info_desc">1.1. 文档说明</a>
						</li>
						<li>
							<a href="#_env_info">1.2. 请求基路径</a>
						</li>
						<li>
							<a href="#_common_param">1.3. 公共请求参数</a>
						</li>
					</ul>
				</li>
				<li>
					<em></em><a href="#_paths">2. 接口</a>
					<ul>
						<#list moduleList as moduleDataInfo>
							<li>
								<em></em><a href="#_module_${moduleDataInfo.module.name}">2.${moduleDataInfo_index + 1}. ${moduleDataInfo.module.name}</a>
								<ul>
									<#list moduleDataInfo.interList as interDataInfo>
										<li>
											<a href="#_inter_${moduleDataInfo_index + 1}_${interDataInfo_index + 1}">2.${moduleDataInfo_index + 1}.${interDataInfo_index + 1}. <span style='${(interDataInfo.inter.deprecated)?string("text-decoration:line-through;","")}'>${interDataInfo.inter.name}</span></a>
										</li>
									</#list>
								</ul>
							</li>
						</#list>
					</ul>
				</li>
				<li>
					<em></em><a href="#_definitions">3. 数据结构</a>
					<ul>
						<#list moduleList as moduleDataInfo>
							<li>
								<em></em><a href="#_resp_schema_module_${moduleDataInfo.module.name}">3.${moduleDataInfo_index + 1}. ${moduleDataInfo.module.name}</a>
								<ul>
									<#list moduleDataInfo.respSchemaList as respSchemaInfo>
										<li>
											<a href="#_resp_schema_${respSchemaInfo.code}">3.${moduleDataInfo_index + 1}.${respSchemaInfo_index + 1}. ${respSchemaInfo.code}</a>
										</li>
									</#list>
								</ul>
							</li>
						</#list>
					</ul>
				</li>
				<li>
					<em></em><a href="#_error_code">4. 错误码</a>
					<ul>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="sect1">
			<h2>
				<a class="anchor" name="_overview" href="#_overview"></a>1. 概述
			</h2>
			<div class="sectionbody">
				<div class="sect2">
					<h3>
						<a class="anchor" name="_doc_info_desc" href="#_doc_info_desc"></a>
						1.1. 文档说明
					</h3>
					<div class="paragraph">
						${apiDoc.description}
					</div>
				</div>
				<div class="sect2">
					<h3>
						<a class="anchor" name="_env_info" href="#_env_info"></a>
						1.2. 请求基路径
					</h3>
					<div class="paragraph">
						<table class="tableblock frame-all grid-all spread">
							<thead>
								<tr>
									<th class="tableblock halign-left valign-middle" style="width: 16%;">名称</th>
									<th class="tableblock halign-left valign-middle" style="width: 40%;">基路径</th>
									<th class="tableblock halign-left valign-middle" style="width: 33%;">描述</th>
									<th class="tableblock halign-left valign-middle" style="width: 10%;">状态</th>
								</tr>
							</thead>
							<#if (envList?? && (envList?size) > 0)>
								<tbody>
									<#list envList as envInfo>
										<tr>
											<td class="tableblock halign-left valign-middle">${envInfo.name}</td>
											<td class="tableblock halign-left valign-middle">${envInfo.baseUrl}</td>
											<td class="tableblock halign-left valign-middle">${envInfo.description}</td>
											<td class="tableblock halign-left valign-middle">${(envInfo.status == "on")?string("启用", "关闭")}</td>
										</tr>
									</#list>
								</tbody>
							</#if>
						</table>
					</div>
				</div>
				<div class="sect2">
					<h3>
						<a class="anchor" name="_common_param" href="#_common_param"></a>
						1.3. 公共请求参数
					</h3>
					<div class="paragraph">
						<table class="tableblock frame-all grid-all spread">
							<thead>
								<tr>
									<th class="tableblock halign-left valign-middle" style="width: 16%;">字段</th>
									<th class="tableblock halign-left valign-middle" style="width: 12%;">参数位置</th>
									<th class="tableblock halign-left valign-middle" style="width: 12%;">类型</th>
									<th class="tableblock halign-left valign-middle" style="width: 16%;">默认值</th>
									<th class="tableblock halign-left valign-middle" style="width: 10%;">必输项</th>
									<th class="tableblock halign-left valign-middle" style="width: 33%;">描述</th>
								</tr>
							</thead>
							<#if (commonParamList?? && (commonParamList?size) > 0)>
								<tbody>
									<#list commonParamList as interParam>
										<tr>
											<td class="tableblock halign-left valign-middle">${interParam.code}</td>
											<td class="tableblock halign-left valign-middle">${interParam.position}</td>
											<td class="tableblock halign-left valign-middle">${interParam.type.code}</td>
											<td class="tableblock halign-left valign-middle">${interParam.defValue}</td>
											<td class="tableblock halign-left valign-middle">${(interParam.required)?string("是","否")}</td>
											<td class="tableblock halign-left valign-middle">${interParam.description}</td>
										</tr>
									</#list>
								</tbody>
							</#if>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="sect1">
			<h2>
				<a class="anchor" name="_paths" href="#_paths"></a>2. 接口
			</h2>
			<div class="sectionbody">
				<#list moduleList as moduleDataInfo> 
					<div class="sect2">
						<h3>
							<a class="anchor" name="_module_${moduleDataInfo.module.name}" href="#_module_${moduleDataInfo.module.name}"></a>
							2.${moduleDataInfo_index + 1}. ${moduleDataInfo.module.name}
						</h3>
						<div class="paragraph">
							${moduleDataInfo.module.description}
						</div>
						
						<#list moduleDataInfo.interList as interDataInfo>
							<div class="sect3">
								<h4>
									<a class="anchor" name="_inter_${moduleDataInfo_index + 1}_${interDataInfo_index + 1}" href="#_inter_${moduleDataInfo_index + 1}_${interDataInfo_index + 1}"></a>
									2.${moduleDataInfo_index + 1}.${interDataInfo_index + 1}. <span style='${(interDataInfo.inter.deprecated)?string("text-decoration:line-through;","")}'>${interDataInfo.inter.name}</span>
								</h4>
								<div class="paragraph">
									${interDataInfo.inter.description}
								</div>
								<div class="sect4">
									<h5>
										<a class="anchor" name="_inter_${interDataInfo.inter.name}_basic" href="#_inter_${interDataInfo.inter.name}_basic"></a>基本信息
									</h5>
									<div class="ulist">
										<ul>
											<li>
												<p>
													请求url：<code>${interDataInfo.inter.path}</code>
												</p>
											</li>
											<li>
												<p>
													是否弃用：<code>${(interDataInfo.inter.deprecated)?string("是","否")}</code>
												</p>
											</li>
											<li>
												<p>
													是否忽略公共请求参数：<code>${(interDataInfo.inter.skipCommonParam)?string("是","否")}</code>
												</p>
											</li>
											<li>
												<p>
													请求协议：<code>${interDataInfo.inter.scheme}</code>
												</p>
											</li>
											<#if (interDataInfo.inter.scheme != 'WS') && (interDataInfo.inter.scheme != 'WWS')>
												<li>
													<p>
														请求方式：<code>${interDataInfo.inter.method}</code>
													</p>
												</li>
											</#if>
											<li>
												<p>
													请求格式：<code>${interDataInfo.inter.consume}</code>
												</p>
											</li>
											<li>
												<p>
													响应格式：<code>${interDataInfo.inter.produce}</code>
												</p>
											</li>
											
											<#if interDataInfo.inter.devStatus != "none">
												<li>
													<p>
														开发状态：<code>${interDataInfo.inter.devStatus.displayName}</code>
													</p>
												</li>
											</#if>
											
											<#if interDataInfo.inter.developer?? && interDataInfo.inter.developer != "" >
												<li>
													<p>
														责任人：<code>${interDataInfo.inter.developer}</code>
													</p>
												</li>
											</#if>
											
											<#if interDataInfo.inter.label?? && interDataInfo.inter.label != "" >
												<li>
													<p>
														标签：<code>${interDataInfo.inter.label}</code>
													</p>
												</li>
											</#if>
											
											<li>
												<p>
													创建时间：<code>${interDataInfo.inter.createDate?string('yyyy-MM-dd HH:mm:ss')}</code>
												</p>
											</li>
											<li>
												<p>
													修改时间：<code>${interDataInfo.inter.modifyDate?string('yyyy-MM-dd HH:mm:ss')}</code>
												</p>
											</li>
										</ul>
									</div>
								</div>
								<div class="sect4">
									<h5>
										<a class="anchor" name="_inter_${interDataInfo.inter.name}_param" href="#_inter_${interDataInfo.inter.name}_param"></a>请求参数
									</h5>
									<table class="tableblock frame-all grid-all spread">
										<thead>
											<tr>
												<th class="tableblock halign-left valign-middle" style="width: 16%;">字段</th>
												<th class="tableblock halign-left valign-middle" style="width: 12%;">参数位置</th>
												<th class="tableblock halign-left valign-middle" style="width: 10%;">类型</th>
												<th class="tableblock halign-left valign-middle" style="width: 12%;">默认值</th>
												<th class="tableblock halign-left valign-middle" style="width: 10%;">必输项</th>
												<th class="tableblock halign-left valign-middle" style="width: 16%;">结构</th>
												<th class="tableblock halign-left valign-middle" style="width: 23%;">描述</th>
											</tr>
										</thead>
										<#if (interDataInfo.paramList?? && ((interDataInfo.paramList)?size) > 0)>
											<tbody>
												<#list interDataInfo.paramList as interParam>
													<tr>
														<td class="tableblock halign-left valign-middle">${interParam.code}</td>
														<td class="tableblock halign-left valign-middle">${interParam.position}</td>
														<td class="tableblock halign-left valign-middle">${interParam.type.code}</td>
														<td class="tableblock halign-left valign-middle">${interParam.defValue}</td>
														<td class="tableblock halign-left valign-middle">${(interParam.required)?string("是","否")}</td>
														<td class="tableblock halign-left valign-middle">
															<#if (interParam.type.code) == "cust_json">
																<p class="tableblock">
																	&lt;<a href="#_inter_${interDataInfo.inter.id}_param_json_schema_${interParam.code}">${interParam.code}</a>&gt;
																</p>
															</#if>
															
															<#if (interParam.type.code) == "ref">
																<p class="tableblock">
																	&lt;<a href="#_resp_schema_${interParam.refSchemaCode}">${interParam.refSchemaCode!"无效"}</a>&gt;
																</p>
															</#if>
														</td>
														<td class="tableblock halign-left valign-middle">${interParam.description}</td>
													</tr>
												</#list>
											</tbody>
										</#if>
									</table>
								</div>
								
								<#if interDataInfo.paramExtSchemaMap?exists>
									<div class="sect4">
										<#list (interDataInfo.paramExtSchemaMap)?keys as schemaCode>
											<div class="cust-paragraph">
												<a name="_inter_${interDataInfo.inter.id}_param_json_schema_${schemaCode}" href="#_inter_${interDataInfo.inter.id}_param_json_schema_${schemaCode}"></a>
												[<span class="ref-title">${schemaCode}</span>]
											</div>
											<div class="content">
												<p class="schema-content">${(interDataInfo.paramExtSchemaMap)[schemaCode]}</p>
											</div>
										</#list>
									</div>
								</#if>
								
								<div class="sect4">
									<h5>
										<a class="anchor" name="_inter_${interDataInfo.inter.name}_resp" href="#_inter_${interDataInfo.inter.name}_resp"></a>请求响应
									</h5>
									<table class="tableblock frame-all grid-all spread">
										<thead>
											<tr>
												<th class="tableblock halign-left valign-middle" style="width: 16%;">名称</th>
												<th class="tableblock halign-left valign-middle" style="width: 12%;">类型</th>
												<th class="tableblock halign-left valign-middle" style="width: 10%;">默认</th>
												<th class="tableblock halign-left valign-middle" style="width: 22%;">结构</th>
												<th class="tableblock halign-left valign-middle" style="width: 39%;">描述</th>
											</tr>
										</thead>
										<#if (interDataInfo.respList?? && ((interDataInfo.respList)?size) > 0)>
											<tbody>
												<#list interDataInfo.respList as schemaInfo>
													<tr>
														<td class="tableblock halign-left valign-middle">${schemaInfo.code}</td>
														<td class="tableblock halign-left valign-middle">${schemaInfo.type.code}</td>
														<td class="tableblock halign-left valign-middle">${(schemaInfo.def)?string("是","否")}</td>
														<td class="tableblock halign-left valign-middle">
															<#if (schemaInfo.type.code) == "cust_json">
																<p class="tableblock">
																	&lt;<a href="#_inter_${interDataInfo.inter.id}_resp_json_schema_${schemaInfo.code}">${schemaInfo.code}</a>&gt;
																</p>
															</#if>
															
															<#if (schemaInfo.type.code) == "object" || (schemaInfo.type.code) == "array">
																<p class="tableblock">
																	&lt;<a href="#_inter_${interDataInfo.inter.id}_resp_schema_${schemaInfo.code}">${schemaInfo.code}</a>&gt;
																</p>
															</#if>
															
															<#if (schemaInfo.type.code) == "ref">
																<p class="tableblock">
																	&lt;<a href="#_resp_schema_${schemaInfo.refSchemaCode}">${schemaInfo.refSchemaCode!"无效"}</a>&gt;
																</p>
															</#if>
														</td>
														<td class="tableblock halign-left valign-middle">${schemaInfo.description}</td>
													</tr>
												</#list>
											</tbody>
										</#if>
									</table>
								</div>
								
								<#if interDataInfo.respExtSchemaMap?exists>
									<div class="sect4">
										<#list (interDataInfo.respExtSchemaMap)?keys as schemaCode>
											<div class="cust-paragraph">
												<a name="_inter_${interDataInfo.inter.id}_resp_json_schema_${schemaCode}" href="#_inter_${interDataInfo.inter.id}_resp_json_schema_${schemaCode}"></a>
												[<span class="ref-title">${schemaCode}</span>]
											</div>
											<div class="content">
												<p class="schema-content">${(interDataInfo.respExtSchemaMap)[schemaCode]}</p>
											</div>
										</#list>
									</div>
								</#if>
								
								<#if interDataInfo.respCustComplexSchemaMap?exists>
									<div class="sect4">
										<#list (interDataInfo.respCustComplexSchemaMap)?keys as schemaCode>
											<div class="cust-paragraph">
												<a name="_inter_${interDataInfo.inter.id}_resp_schema_${schemaCode}" href="#_inter_${interDataInfo.inter.id}_resp_schema_${schemaCode}"></a>
												[<span class="ref-title">${schemaCode}</span>]
											</div>
											<table class="tableblock frame-all grid-all spread">
												<thead>
													<tr>
														<th class="tableblock halign-left valign-middle" style="width: 28%;">字段</th>
														<th class="tableblock halign-left valign-middle" style="width: 10%;">类型</th>
														<th class="tableblock halign-left valign-middle" style="width: 10%;">非空</th>
														<th class="tableblock halign-left valign-middle" style="width: 22%;">结构</th>
														<th class="tableblock halign-left valign-middle" style="width: 30%;">描述</th>
													</tr>
												</thead>
												<#if ((interDataInfo.respCustComplexSchemaMap[schemaCode])?? && ((interDataInfo.respCustComplexSchemaMap[schemaCode])?size) > 0)>
													<tbody>
														<#list (interDataInfo.respCustComplexSchemaMap[schemaCode]) as schemaInfo>
															<tr>
																<td class="tableblock halign-left valign-middle">${schemaInfo.code}</td>
																<td class="tableblock halign-left valign-middle">${schemaInfo.type.code}</td>
																<td class="tableblock halign-left valign-middle">${(schemaInfo.required)?string("是","否")}</td>
																<td class="tableblock halign-left valign-middle">
																	<#if (schemaInfo.type.code) == "ref">
																		<p class="tableblock">
																			&lt;<a href="#_resp_schema_${schemaInfo.refSchemaCode}">${schemaInfo.refSchemaCode!"无效"}</a>&gt;
																		</p>
																	</#if>
																</td>
																<td class="tableblock halign-left valign-middle">${schemaInfo.description}</td>
															</tr>
														</#list>
													</tbody>
												</#if>
											</table>
										</#list>
									</div>
								</#if>
							</div>
						</#list>
					</div>
				</#list>
			</div>
		</div>
		<div class="sect1">
			<h2>
				<a class="anchor" name="_definitions" href="#_definitions"></a>3. 数据结构
			</h2>
			<div class="sectionbody">
				<#list moduleList as moduleDataInfo>
					<div class="sect2">
						<h3>
							<a class="anchor" name="_resp_schema_module_${moduleDataInfo.module.name}" href="#_resp_schema_module_${moduleDataInfo.module.name}"></a>3.${moduleDataInfo_index + 1}. ${moduleDataInfo.module.name}
						</h3>
						
						<#list moduleDataInfo.respSchemaList as respSchemaInfo>
							<div class="sect3">
								<h4>
									<a class="anchor" name="_resp_schema_${respSchemaInfo.code}" href="#_resp_schema_${respSchemaInfo.code}"></a>
									3.${moduleDataInfo_index + 1}.${respSchemaInfo_index + 1}. ${respSchemaInfo.code}
								</h4>
								<div class="cust-paragraph">
									${respSchemaInfo.description}
								</div>
								
								<div class="sect4">
									<table class="tableblock frame-all grid-all spread">
										<thead>
											<tr>
												<th class="tableblock halign-left valign-middle" style="width: 28%;">字段</th>
												<th class="tableblock halign-left valign-middle" style="width: 10%;">类型</th>
												<th class="tableblock halign-left valign-middle" style="width: 10%;">非空</th>
												<th class="tableblock halign-left valign-middle" style="width: 22%;">结构</th>
												<th class="tableblock halign-left valign-middle" style="width: 30%;">描述</th>
											</tr>
										</thead>
										<#if (respSchemaInfo.childList?? && ((respSchemaInfo.childList)?size) > 0)>
											<tbody>
												<#list respSchemaInfo.childList as schemaInfo>
													<tr>
														<td class="tableblock halign-left valign-middle">${schemaInfo.code}</td>
														<td class="tableblock halign-left valign-middle">${schemaInfo.type.code}</td>
														<td class="tableblock halign-left valign-middle">${(schemaInfo.required)?string("是","否")}</td>
														<td class="tableblock halign-left valign-middle">
															<#if (schemaInfo.type.code) == "ref">
																<p class="tableblock">
																	&lt;<a href="#_resp_schema_${schemaInfo.refSchemaCode}">${schemaInfo.refSchemaCode!"无效"}</a>&gt;
																</p>
															</#if>
														</td>
														<td class="tableblock halign-left valign-middle">${schemaInfo.description}</td>
													</tr>
												</#list>
											</tbody>
										</#if>
									</table>
								</div>
							</div>
						</#list>
					</div>
				</#list>
			</div>
		</div>
		
		<div class="sect1">
			<h2>
				<a class="anchor" name="_error_code" href="#_error_code"></a>4. 错误码
			</h2>
			<div class="sectionbody">
				<table class="tableblock frame-all grid-all spread">
					<thead>
						<tr>
							<th class="tableblock halign-left valign-middle" style="width: 16%;">返回码</th>
							<th class="tableblock halign-left valign-middle" style="width: 30%;">返回信息</th>
							<th class="tableblock halign-left valign-middle" style="width: 53%;">说明</th>
						</tr>
					</thead>
					<#if (errorCodeList?? && (errorCodeList?size) > 0)>
						<tbody>
							<#list errorCodeList as errorCode>
								<tr>
									<td class="tableblock halign-left valign-middle">${errorCode.code}</td>
									<td class="tableblock halign-left valign-middle">${errorCode.msg}</td>
									<td class="tableblock halign-left valign-middle">${errorCode.description}</td>
								</tr>
							</#list>
						</tbody>
					</#if>
				</table>
			</div>
		</div>
	</div>
	<div id="footer">
		<div id="footer-text">生成时间：${buildDate}</div>
	</div>
	
	<script type="text/javascript">
		(function(e) {
			for (var _obj = document.getElementById(e.id).getElementsByTagName(e.tag), i = -1, em; em = _obj[++i];) {
				em.onclick = function() { //onmouseover
					var ul = this.nextSibling;
					if (!ul) {
						return false;
					}
					ul = ul.nextSibling;
					if (!ul) {
						return false;
					}
					if (e.tag != 'a') {
						ul = ul.nextSibling;
						if (!ul) {
							return false;
						}
					} //a 标签控制 隐藏或删除该行
					for (var _li = this.parentNode.parentNode.childNodes, n = -1, li; li = _li[++n];) {
						if (li.tagName == "LI") {
							for (var _ul = li.childNodes, t = -1, $ul; $ul = _ul[++t];) {
								switch ($ul.tagName) {
									case "UL":
										$ul.className = $ul != ul ? ""
												: ul.className ? "" : "off";
										break;
									case "EM":
										$ul.className = $ul != this ? ""
												: this.className ? "" : "off";
										break;
								}
							}
						}
					}
				}
			}
		})({
			id : 'toc',
			tag : 'em'
		});
	</script>
</body>
</html>