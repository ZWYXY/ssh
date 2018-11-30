<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_listCondition.action?currentPage=1"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" maxLength=50 name="custName"></TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系方式</TD>
													<TD>联系人</TD>
													<TD colspan="2" align="center">操作</TD>
												</TR>
                                                <%--放大域对象中使用jstl foreach标签取 ,当时用值栈时这么取 性能低--%>
<%--												<c:forEach items="${list }" var="customer">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.custName }</TD>
													<TD>${customer.custLevel }</TD>
													<TD>${customer.custSource }</TD>
													<TD>${customer.custPhone }</TD>
													<TD>${customer.custLinkman }</TD>
													<TD>
													
													</TD>
												</TR>
												
												</c:forEach>--%>
                                            <%-- 使用值栈时，这么取值 用Struts2的标签 但是需要提前引入Struts2标签库 EL表达式可以取值栈中的数据
                                            		其他域的数据用这种从值栈中取值的方式取不到
                                            --%>
                                            <s:iterator value="pageBean.list" var="cus">
                                                <TR
                                                        style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
                                                    <TD>
                                                        <s:property value="#cus.custName"/>
                                                    </TD>
                                                    <TD>
                                                        <s:property value="#cus.custLevel"/>
                                                    </TD>
                                                    <TD>
                                                        <s:property value="#cus.custSource"/>
                                                    </TD>
                                                    <TD>
                                                        <s:property value="#cus.custPhone"/>
                                                    </TD>
                                                    <TD>
                                                        <s:property value="#cus.custLinkman"/>
                                                    </TD>

<TD>
	<a href="${PageContext.request.contextPath}/customer_showCustomer.action?cid=<s:property value="#cus.cid"/>">修改</a>
</TD>
&nbsp;&nbsp;
<TD>
	<a href="${PageContext.request.contextPath}/customer_delete.action?cid=<s:property value="#cus.cid"/>">删除</a>
</TD>
                                                </TR>
                                            </s:iterator>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
<DIV
	style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
	共[<B>${pageBean.totalCount}</B>]条记录,[<B>${pageBean.totalPage}</B>]页,当前第[<B>${pageBean.currentPage}</B>]页
	<%--
		判断如果是第一页，则没有前一页
		判断如果是最后一页，则没有后一页
	--%>
	<c:if test="${pageBean.currentPage != 1}">
		[<a href="${pageContext.request.contextPath}/customer_list.action?currentPage=${pageBean.currentPage - 1}">
			前一页
		</a>]
	</c:if>
	<c:if test="${pageBean.currentPage != pageBean.totalPage}">
		[<a href="${pageContext.request.contextPath}/customer_list.action?currentPage=${pageBean.currentPage + 1}">
			后一页
		</a>]
	</c:if>
	<input type="hidden" size="" id="page" name="page" />
</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
