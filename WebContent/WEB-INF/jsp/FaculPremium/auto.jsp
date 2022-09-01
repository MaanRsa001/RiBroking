<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <sj:head jqueryui="true" jquerytheme="start"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script language="JavaScript" type="text/javascript">
    </script>
    <link href="${pageContext.request.contextPath}/css/autoProcess.css" type="text/css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/dataTables/css/jquery-start-ui.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	 $(function() {
		$( "#dueDate" ).datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd/mm/yy"
			//yearRange: "-100:+0"
		});
		
	  });	
	  </script>														 


</head>
<body>
<div class="table0" style="width: 100%; margin: 0 auto;">
    <div class="tablerow">
        <div class="table1" style="width: 100%; margin: 0 auto; background-color: #E5E5E5; ">
            <div class="tablerow">
                <div style="padding:10px; background:#F8F8F8">

                    <div class="table2">
                        <form action="?" method="get">
                        
                            <div class="tablerow">
                                <div class="panel panel-primary">
                                    <div class="panel-body">
                                        <div class="boxcontent">
                                            <div class="textfield">
                                                <div class="text">
                                                    <s:text name="label.installmentsDue"/>
                                                </div>
                                                <div class="tbox">
                                                    <div >
                                                        <%--class="dueDate" --<sj:datepicker name="dueDate" id="dueDate" displayFormat="dd/mm/yy" cssClass="dp1 inputBoxS pullLeft"/>--%>
                                                        <s:textfield name="dueDate" id="dueDate"  cssClass="inputBox"   />
                                                    </div>
                                                </div>
                                            </div>
                                            <br class="clear">
                                        </div>
                                        <br>

                                        <div align="center">
                                            <button type="submit" class="btn btn-sm btn-warning">
                                                <i class="glyphicon glyphicon-search"></i> Search
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <input type="hidden" id="mode" name="mode" value="${mode}"/>
                        <input type="hidden" id="productId" name="productId" value="${productId}"/>

                        <div class="tablerow">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <c:choose>
                                        <c:when test="${productId == '1'}">
                                        
                                            <s:text name="allocated.facltative"/>
                                        </c:when>
                                        <c:when test="${productId == '3'}">
                                            <s:text name="allocated.XOL"/>
                                        </c:when>
                                    </c:choose>
                                    &nbsp;
                                    <s:if test='!"error".equals(mode) && !"error".equals(transactionError)'>
                                    <div class="pull-right">
                                        <div>
                                            <button class="btn btn-default btn-sm dropdown-toggle" type="button" id="menu1" data-toggle="dropdown" style="margin-top: -4px">Items
                                                per page
                                                <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
                                                <li role="presentation" class="perPageToggle p10" data-page="10"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check text-blue"></i> 10 items</a></li>
                                                <li role="presentation" class="perPageToggle p20" data-page="20"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 20 items</a></li>
                                                <%--<li role="presentation" class="divider"></li>--%>
                                                <li role="presentation" class="perPageToggle p50" data-page="50"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 50 items</a></li>
                                                <li role="presentation" class="perPageToggle p100" data-page="100"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> 100 items</a></li>
                                            </ul>
                                            <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" style="margin-top: -4px">Checkbox Toggle
                                                <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
                                                <li role="presentation" class="checkToggle check"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check"></i> Check All</a></li>
                                                <li role="presentation" class="checkToggle uncheck"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked"></i> UnCheck All</a></li>
                                                <li role="presentation" class="divider"></li>
                                                <li role="presentation" class="checkToggle page check"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-check text-blue"></i> Check All (page)</a></li>
                                                <li role="presentation" class="checkToggle page uncheck"><a role="menuitem" tabindex="-1" href="#"><i
                                                        class="glyphicon glyphicon-unchecked text-blue"></i> UnCheck All (page)</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                   </s:if>
                                </div>
                                <div class="panel-body" style="overflow: auto;">
                                    <s:set name="results" value="portfolioList"/>
                                    <s:set var="noOfResults" name="noOfResults" value="#results!= null ? #results.size() : 0"/>
                                    <div class="tablerow">
                                        <span style="color:red;"><s:actionerror/></span>
                                        <span style="color:green;"><s:actionmessage/></span>
                                    </div>
                                    <form action="" id="postForm" name="postForm"  method="post">
                                    <s:if test='!"error".equals(mode) && !"error".equals(transactionError)'>
                                        <c:choose>
                                            <c:when test="${noOfResults>0}">
                                                <h4 id="loader">Loading.. Please wait</h4>

                                                <div class="table-wrapper" id="dvData">
                                                    <display:table name="portfolioList" requestURI="" export="false" class="footable record_editable"
                                                                   style="width:2400px;display:none;"
                                                                   uid="row" id="record">
                                                        <display:setProperty name="paging.banner.one_item_found" value=""/>
                                                        <display:setProperty name="paging.banner.one_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.all_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.some_items_found" value=""/>
                                                        <display:setProperty name="paging.banner.placement" value="bottom"/>
                                                        <display:setProperty name="paging.banner.onepage" value=""/>
	
                                                        <s:set name="myrow" value="#attr.record"/>
                                                        <display:column sortable="true" style="text-align:center;" class="sid" title="Sno">
                                                            <s:property value="%{#attr.record_rowNum}"/>
                                                            <!--<input type="hidden" name="installmentNo" value="${record.installmentNo}"/>
                                                            --><input type="hidden" name="departmentId" value="${record.departmentId}"/>
                                                            <input type="hidden" name="proposalNo" value="${record.proposalNo}"/>
                                                            <input type="hidden" name="premiumClass" value="${record.premiumClass}"/>
                                                            <input type="hidden" name="premiumSubClass" value="${record.premiumSubClass}"/>
                                                            <input type="hidden" name="acceptenceDate" value="${record.acceptenceDate}"/>
                                                            <input type="hidden" name="rowNumber" value="${attr.record_rowNum}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Contract No" class="w80 " style="width:120px;">
                                                            <input type="text" readonly="readonly" name="contractNo" value="${record.contractNo}"/></display:column>
                                                        <c:choose>
                                                            <c:when test="${productId=='3'}">
                                                                <display:column sortable="true" title="Layer No" class="w80 textCenter">
                                                                    <input type="text" readonly="readonly" name="layerNo" value="${record.layerNo}"/> </display:column>
                                                            </c:when>
                                                        </c:choose>
                                                        <display:column sortable="true" title="UW Year" class="w80">
                                                            <input type="number" name="uwYear" readonly="readonly" value="${record.uwYear}" min="1991" max="2121"/> </display:column>
                                                       <c:choose>
                                                            <c:when test="${productId!='3'}">
                                                        <display:column sortable="true" title="Sub Profit Center" class="w120">
                                                            <input type="text" readonly="readonly" name="subClass" value="${record.subClass}"/> </display:column>
                                                             </c:when>
                                                        </c:choose>
                                                        <display:column sortable="true" title="Company Name" class="w200">
                                                            <input type="text" readonly="readonly" name="ceding_Company_Name" value="${record.ceding_Company_Name}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Broker Name" class="w200">
                                                            <input type="text" readonly="readonly" name="brokerName" value="${record.brokerName}"/> </display:column>
                                                        <c:choose>
                                                            <c:when test="${productId=='1'}">
                                                                <display:column sortable="true" title="Insured Name" class="w200">
                                                                    <input type="text" readonly="readonly" name="insuredName" value="${record.insuredName}"/> </display:column>
                                                            </c:when>
                                                            <c:when test="${productId=='3'}">
                                                                <display:column sortable="true" title="Treaty Name" class="w200">
                                                                    <input type="text" readonly="readonly" name="treatyName" value="${record.treatyName}"/> </display:column>
                                                            </c:when>
                                                        </c:choose>
                                                        <display:column sortable="true" title="Inception Date" class="w80">
                                                            <input type="text" readonly="readonly" name="inception_Date" value="${record.inception_Date}"/> </display:column>
                                                        <display:column sortable="true" title="Expiry Date" class="w80">
                                                            <input type="text" readonly="readonly" name="expiry_Date" value="${record.expiry_Date}"/> </display:column>
                                                        <display:column sortable="true" title="Signed Share" class="w80 textRight">
                                                            <input type="text" readonly="readonly" name="shareSign" value="${record.shareSign}"/> </display:column>
                                                            <display:column sortable="true" title="Installment No" class="w80 textBold">
                                                            <input type="text" readonly="readonly" name="installmentNo" value="${record.installmentNo}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Installment Date" class="w80 textBold">
                                                            <input type="text" readonly="readonly" name="installmentDate" value="${record.installmentDate}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Transaction Date" class="w80">
                                                            <%--<sj:datepicker name="transactionDate" displayFormat="dd/mm/yy" cssClass="dp1"/>--%>
                                                            <input type="text" class="dp1 date" name="transactionDate" value="${record.transactionDate}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Statement Date" class="w80">
                                                            <%--<sj:datepicker name="receiveDate" displayFormat="dd/mm/yy" cssClass="dp1"/>--%>
                                                            <input type="text" class="dp1 date" name="receiveDate" value="${record.receiveDate}"/>
                                                        </display:column>
                                                        <display:column sortable="true" title="Cedent / Broker Reference" class="w200">
                                                            <input type="text" readonly="readonly" name="reference" value="Installment Auto Posting"/>
                                                        </display:column>

                                                        <display:column sortable="true" title="Currency" class="w80 textCenter">
                                                            <input type="hidden" name="currencyId" value="${record.currencyId}"/>
                                                            <input type="text" readonly="readonly" name="currencyShort" value="${record.currencyShort}"/> </display:column>
                                                        <display:column sortable="true" title="Exchange Rate" class="w120 textRight">
                                                            <input type="text" readonly="readonly" name="exchangeRate" value="${record.exchangeRate}"/> </display:column>
                                                        <c:choose>
                                                            <c:when test="${productId == '1'}">
                                                                <display:column sortable="true" title="Premium" class="w80 premium textRight">
                                                                    <input type="text" readonly="readonly" class="premiumOC" name="premiumOC"
                                                                           value="${record.premiumOC}"/> </display:column>
                                                            </c:when>
                                                            <c:when test="${productId == '3'}">
                                                                <display:column sortable="true" title="Deposit Premium" class="w80 premium textRight">
                                                                     <input type="text" readonly="readonly" class="premiumOC" name="premiumOC"
                                                                           value="${record.premiumOC}"/> </display:column>
                                                            </c:when>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${productId=='1'}">
                                                                <display:column sortable="true" title="Commission" class="w80 cost textRight">
                                                                    <input type="hidden" name="commission" value="${record.commission}"/>
                                                                    <input type="text" name="commissionOC" value="${record.commissionOC}"
                                                                           data-cost="${record.commission}"/> </display:column>
                                                            </c:when>
                                                        </c:choose>
                                                        <display:column sortable="true" title="Tax Deducted at Source" class="w80 cost1 textRight">
                                                             <input type="text" name="faculPremiumBean.taxDedectSource" value="${record.faculPremiumBean.taxDedectSource}"
                                                                   placeholder="0"/> </display:column>
                                                        <display:column sortable="true" title="Service Tax" class="w80 cost1 textRight">
                                                            <input type="text" name="faculPremiumBean.serviceTax" value="${record.faculPremiumBean.serviceTax}"
                                                                   placeholder="0"/> </display:column>
                                                        <display:column sortable="true" title="Brokerage" class="w80 cost textRight">
                                                            <input type="hidden" name="brokerage" value="${record.brokerage}"/>
                                                            <input type="text" name="brokerageOC" value="${record.brokerageOC}" data-cost="${record.brokerage}"/> </display:column>
                                                        <display:column sortable="true" title="Tax Expenses" class="w80 cost textRight">
                                                            <input type="hidden" name="tax" value="${record.tax}"/>
                                                            <input type="text" name="taxOC" value="${record.taxOC}" data-cost="${record.tax}"/> </display:column>
                                                        <display:column sortable="true" title="With Holding Tax Recoverable" class="w80 cost textRight">
                                                            <input type="text" name="faculPremiumBean.withHoldingTaxOC" value="${record.faculPremiumBean.withHoldingTaxOC}"
                                                                   placeholder="0"/> </display:column>
                                                          <display:column sortable="true" title="Bonus" class="w80 cost textRight">
                                                            <input type="text" name="faculPremiumBean.bonus" value="${record.faculPremiumBean.bonus}"
                                                                   placeholder="0"/> </display:column>          
                                                        <display:column sortable="true" title="Other Cost" class="w80 cost textRight">
                                                            <input type="hidden" name="otherCost" value="${record.otherCost}"/>
                                                            <input type="text" name="otherCostOC" value="${record.otherCostOC}" data-cost="${record.otherCost}"/> </display:column>
                                                        <display:column sortable="true" title="Net Due" class="w80 textRight">
                                                            <input type="text" readonly="readonly" name="faculPremiumBean.netDue" value="${record.netDueOC}"/> </display:column>
                                                        <display:column sortable="true" title="Remarks" class="w120">
                                                            <input type="text" name="remarks" value="${record.remarks}"/> </display:column>
                                                        <display:column sortable="true" title="Post" class="w80 toggleCheck">
                                                            <div class="msmsCheck">
                                                                <input type="checkbox" checked="checked" value="None" id="isPost${attr.record_rowNum}" name="isPost"/>
                                                                <label for="isPost${attr.record_rowNum}"></label>
                                                            </div>
                                                        </display:column>
                                                    </display:table>
                                                </div>
                                                <div>
                                                    <ul id="paging"></ul>
                                                </div>
                                                <div class="btnContainer" style="text-align: center;">
                                                    <button class="btn btn-sm btn-primary btnPrintView" type="button">Export View</button>
                                                    <s:if test='!"Yes".equals(autoopenDate)'>
                                                    <button class="btn btn-sm btn-primary btnProcess" type="button">Process All</button>
                                                    <button class="btn btn-sm btn-primary btnProcess page" type="button">Process Page</button>
                                                    </s:if> 
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <h4>No data found..</h4>
                                            </c:otherwise>
                                        </c:choose>
                                       </s:if>
                                    </form>
                                    <form action="" method="post" id="dummyForm"></form>
                                    <table id="tableForm"></table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var openPeriod = {
        start: false,
        end: false
    };
    var itemsPerPage = 10;
    $(function () {
        var curPage = 1;
        _$.fn.datepicker = $.fn.datepicker; //copy function from old jquery
        _$.datepicker = $.datepicker;
        (function ($) {
            var mode = $('input[name="mode"]');
            var dueDateRaw = $('[name="dueDate"]').first();
            var dVal = dueDateRaw.attr('value').split(',');
            var dueDate = dueDateRaw.val((dVal.length > 0) ? dVal[0] : dVal).val();
            var btnContainer = $('.btnContainer').first();
            var boxContent = $('.boxcontent').first();
            //handle post form
            var postForm = $('#postForm').on('submit', function () {
//                if(!confirm("Have you checked all the entries?  Processing will be done for all the marked items. Do you wish to continue?"))return false;
                try {
                    var tbody = $(this).find('tbody');
                    var rows = tbody.find('tr');
                    var form = $('#dummyForm').empty();
                    var total = 0;
                    if (postForm.processCurrent)
                        rows = rows.filter(':visible');
                    var markItems = rows.filter(function () {
                        return $(this).find('[name="isPost"]').is(':checked');
                    });
                    var markItemsCount = markItems.length;
                    var msg = (postForm.processCurrent) ?
                    "Have you checked all the entries in the <b>current page</b>?  <br />" +
                    "Processing will be done for all the marked items [" + markItemsCount + " " + ((markItemsCount) > 1 ? 'items' : 'item') + "]. <br />" +
                    "Do you wish to continue?" :
                    "Have you checked all the entries in <b>all pages</b>?  <br />" +
                    "Processing will be done for all the marked items [" + markItemsCount + " " + ((markItemsCount) > 1 ? 'items' : 'item') + "].<br />" +
                    "Do you wish to continue?";
                    if (markItemsCount < 1) {
                        msmsAlert("There are no marked items in your list to process.<br />Please recheck and try again.");
                        return false;
                    }
                    var status=true;
                    $.each(markItems, function () {
                                var row = $(this);                           
                    			if(!dffDateAlert(row.find('[name="acceptenceDate"]').val(),row.find('[name="transactionDate"]').val(),row.find('[name="rowNumber"]').val())){
                    			status = false;
                    			}
                    			});
                    if(status){
                    msmsAlert(msg, {
                        cancelable: true,
                        okFn: function () {
                            if (dueDateRaw.val()) {
                                //form.append(dueDateRaw.clone().hide())
                                    form.append($('<input type="hidden" name="mode" />').val('post'))
                                        .append($('<input type="hidden" name="faculPremiumBean.settlement_status" />').val('Pending'))
                                ;
                            }
                            $.each(markItems, function () {
                                var row = $(this);
//                                var isPost = row.find('[name="isPost"]').is(':checked');
                                //                    console.log(isPost);
//                                if(isPost){
                                var currentId = total++;
                                 $.each(row.find('input'), function () {
                                    var $i = $(this);
                                       
                                    var i = $('<input type="hidden" />')
                                            .attr('name', 'portfolioList[' + currentId + '].' + $i.attr('name'))
                                            .attr('value', $i.val());
                                           // alert('portfolioList[' + currentId + '].' + $i.attr('name'));
                                            //alert($i.val());
                                    form.append(i);
                                });
                               
                                
                            });
                            form.submit();
                        }
                    });
}
                } catch (e) {
                }
//                $.post("", data2sent,function(data){});
//                var data1 = JSON.stringify(data2sent);
                return false;
            });
            btnContainer.find('.btnProcess').on('click', function () {
                var _btn = $(this);
                postForm.processCurrent = _btn.hasClass('page');
                postForm.submit();
                return false;
            });
            btnContainer.find('.btnPrintView').on('click', function () {
                viewPrintList();
                return false;
            });
            var sameDueDate = ["transactionDate", "receiveDate"];
            if (dueDate && mode.val() != 'view') {
                $.each(sameDueDate, function (i, v) {
                    $('[name="' + v + '"]').val(dueDate);
                });
            } else {

            }
            var records = $('#record');
            var loader = $('#loader');
            var pagingHolder = $('ul#paging');

            makePaging(curPage);
            function makePaging(page) {
                records.fadeIn();
                page = page || curPage;
                var btn2show = 3;
                loader.hide();
                var rows = records.find('tbody>tr');
                var maxPage = Math.ceil(rows.length / itemsPerPage);
                var tbody = records.find('tbody').fadeOut(150, function () {
                    rows.hide();
//                    var lastPageItemsLength = maxPage * perPage / rows.length;
                    if (page < 1)page = 1;
                    if (page > maxPage)page = maxPage;
                    var cur = page * itemsPerPage - itemsPerPage;
                    while (++cur <= itemsPerPage * page) {
                        if (cur > rows.length)break;
                        tbody.find(':nth-child(' + cur + ')').show();
                    }
                    tbody.fadeIn();
                });

                curPage = page;
                function makePageBtn() {
                    var i, btn = $('<a href="#" />').addClass('btn btn-xs btn-warning');
                    pagingHolder.empty().append(makeBtn('first'))
                            .append(makeBtn('prev'));
                    for (i = curPage - btn2show; i < curPage; i++) {
                        if (i > 0)
                            pagingHolder.append(makeBtn(i));
                    }
                    pagingHolder.append(makeBtn(curPage));
                    for (i = curPage + 1; i <= maxPage && i <= curPage + btn2show; i++)
                        pagingHolder.append(makeBtn(i));
                    pagingHolder.append(makeBtn('next'))
                            .append(makeBtn('last'));
                    function makeBtn(p) {
                        var text = (p == 'first') ? 'First' : ((p == 'prev') ? 'Prev' : ((p == 'next') ? 'Next' : ((p == 'last') ? 'Last (' + maxPage + ')' : p))),
                                item = $('<li />').append(btn.clone().text(text));
                        if (p == curPage || ((p == 'first' || p == 'prev') && curPage == 1) || ((p == 'last' || p == 'next') && curPage >= maxPage)) {
                            var a = item.on('click', function () {
                                return false;
                            }).find('a').attr('disabled', true).removeClass('btn-warning').addClass((p == curPage) ? 'btn-danger' : 'btn-default');
                        } else {
                            item.on('click', function () {
                                p = (p == 'first') ? 1 : ((p == 'prev') ? curPage - 1 : ((p == 'next') ? curPage + 1 : ((p == 'last') ? maxPage : p)));
                                makePaging(p);
                            });
                        }
                        return item;
                    }

                }

                makePageBtn();
            }

            function validateBeforePost(rows) {

            }

            boxContent.on('dblclick', '[readonly]', function () {
                msmsAlert("This item not available to modified or click!");
            });

            if (mode.val() == 'view') {
                records.find('tr input').prop('disabled', true);
                btnContainer.find('.btnProcess').hide();
                btnContainer.append($('<a href="">').attr("href", "#").addClass("btn btn-warning btn-sm").text("Back").on('click', function () {
                     funBack();
                    //window.history.back();
                    //return false;
                }));
            }
            records.find('input.date').datepicker({
                dateFormat: 'dd/mm/yy',
                onClose: function () {
                    $(this).trigger('dateChange');
                }
            });

//            Event handling
            records.find('.premiumOC,.cost,.cost1>input').on('blur', function () {
                $(this).val(makeMoney(parseMoney($(this).val())));
            });
            records.find('input').on('keyup dateChange', function () {
                if ($(this).hasClass('premiumOC'))fillCost(this);
                checkVal($(this));
            });
            //validate transactionDate
            records.find('input[name="transactionDate"]').on('dateChange', function () {
                var tDate = $(this);
                var row = $(tDate).parents('tr');
                var rDate = row.find('input[name="receiveDate"]');
                try {
                    var _date = $.datepicker.parseDate('dd/mm/yy', tDate.val());
                    var _rDate = $.datepicker.parseDate('dd/mm/yy', rDate.val());
                    if (openPeriod.date && openPeriod.date.start && openPeriod.date.end) {
                        if (!(_date >= openPeriod.date.start && _date <= openPeriod.date.end) || tDate.val().length < 10) {
                            showErr(tDate, true);
                            msmsAlert("Not a valid transaction date, only value <b>between " + openPeriod.start + " and  " + openPeriod.end + " accepted</b>.");
                            tDate.val(dueDate);
                        }
                        showErr(tDate, false);
                    }
                    if (_rDate > getDate(tDate.val()))rDate.val(tDate.val());
                } catch (e) {
                    msmsAlert("Not a valid transaction date, only value <b>between " + openPeriod.start + " and  " + openPeriod.end + " accepted</b>.");
                }
            });
            records.find('input[name="receiveDate"]').on('dateChange', function () {
                var tDate = records.find('input[name="transactionDate"]');
                var rDate = $(this);
                var errMsg = "Not a valid statement receive date. Date must be <b>less than or equal to transaction date</b>";
                try {
                    var _tDate = $.datepicker.parseDate('dd/mm/yy', tDate.val());
                    var _date = $.datepicker.parseDate('dd/mm/yy', rDate.val());
                    if (_tDate && _date) {
                        if (!(_date <= _tDate) || rDate.val().length < 10) {
                            showErr(rDate, true);
                            msmsAlert(errMsg);
                            rDate.val(tDate.val());
                        }
                        showErr(rDate, false);
                    }
                } catch (e) {
                    msmsAlert(errMsg);
                }
            });           

            $.each(records.find('td.sid'), function () {
                if (mode.val() != 'view')
                    fillCost(this);
                checkVal(this);
            });

            records.find('.premiumOC,.cost>input').map(function () {
                $(this).trigger('blur');
            });
            function fillCost(item) {
                item = $(item);
                if (item) {
                    var row = $(item).parents('tr');
                    var pItem = row.find('[name="premiumOC"]');
                    var premium = parseMoney(pItem.val());
                    row.find('.cost>input').each(function () {
                        if ($(this).attr('data-cost')) {
                            var costItem = parseMoney($(this).val(makeMoney((parseMoney($(this).attr('data-cost')) / 100) * (premium || 0))).val()) || 0;
                            $(this).attr('placeholder', makeMoney(costItem));
                            return costItem;
                        }
                    }).get();
                    
       
                }
            }

            function checkVal(item) {
                item = $(item);
                if (item) {
                    var row = item.parents('tr');
                    if (item.hasClass('date')) {
                        if (item.val().length == 10) {
                            getExcRate(item);
                            showErr(item, false);
                        } else {
                            showErr(item, true);
                        }
                    } else {
                        var premium = parseMoney(row.find('[name="premiumOC"]').val());
                        var tax=sumArray(row.find('.cost1>input[type="text"]').map(function () {
                                return parseMoney($(this).val()) || 0;
                            }).get()) || 0;

                        var cost = sumArray(row.find('.cost>input[type="text"]').map(function () {
                                    return parseMoney($(this).val()) || 0;
                                }).get()) || 0;
//                        console.log(premium);
                        row.find('[name="faculPremiumBean.netDue"]').val(makeMoney((premium+tax) - cost));
                    }
                }
            }

            function getExcRate(dateInput) {
             var row = dateInput.parents('tr');
                var url = 'getExcRateRiskDetails.action?dropDown=exchRate';
                $.get(url, {
                    incepDate: row.find('[name="transactionDate"]').first().val(),
                    orginalCurrency: row.find('[name="currencyId"]').first().val()
                }, function (response) {
                    var exRate = parseFloat($(response).filter('#exchRate').val()) || 0;
                    row.find('[name="exchangeRate"]').val(exRate);
                });
            }

            function showErr(input, bool) {
                input = $(input);
                if (bool) {
                    input.css({
                        background: '#fff1e8'
                    });
                } else {
                    input.css({
                        background: ''
                    });
                }
            }

            $.post('openPeriodJson.do', {transactionDate: '01/01/2000'}, function (response) {
                if (!(response && response.model && response.model.valid)) {
                    openPeriod.start = response.model.openPeriodStartDate;
                    openPeriod.end = response.model.openPeriodEndDate;
                    try {
                        openPeriod.date = {
                            start: $.datepicker.parseDate("dd/mm/yy", openPeriod.start),
                            end: $.datepicker.parseDate("dd/mm/yy", openPeriod.end)
                        }
                    } catch (e) {
                    }
                }
            }, 'json');

            function getDate(dateStr, def) {
                try {
                    return $.datepicker.parseDate('dd/mm/yy', dateStr);
                } catch (e) {
                    return def || dateStr;
                }
            }

            //init Checkbox Toggle
            var isOpen = false;
            var dropdownToggle = $('[data-toggle="dropdown"]').on('click', function () {
                var _toggle = $(this), _menu = _toggle.next('ul');
                var container = $('.contents>.boxcontent').first();
                if (!_menu.is(':visible')) {
                    _menu.css({
                        top: _toggle.position().top + _toggle.outerHeight() + container.scrollTop(),
                        left: _toggle.position().left
                    }).fadeIn(function () {
                        isOpen = true;
                    });
                }
                _menu.find('.checkToggle').on('click', function () {
                    var _i = $(this), o = {
                        pageOnly: _i.hasClass('page'),
                        check: _i.hasClass('check')
                    };
                    var cbs = records.find('tbody>tr [name="isPost"]');
                    if (o.pageOnly) cbs = cbs.filter(':visible');
                    cbs.prop('checked', !!(o.check));
                });
                _menu.find('.perPageToggle[data-page]').on('click', function () {
                    var _item = $(this);
                    try {
                        _menu.find('.perPageToggle a>i').removeClass('glyphicon-check').addClass('glyphicon-unchecked');
                        _item.find('a>i').removeClass('glyphicon-unchecked').addClass('glyphicon-check');
                        var p = _item.attr('data-page');
                        itemsPerPage = parseInt(p) || 10;
                        makePaging(1);
                    } catch (e) {
                    }
                });
            }), dropdownMenu = dropdownToggle.next('ul');


            $(document || window).on('click', function () {
                if (isOpen)
                    dropdownMenu.fadeOut(function () {
                        isOpen = false;
                    });
            });

            function msmsAlert(txt, opt) {
                opt = $.extend({okFn: false, cancelable: false}, opt);
                $('.containerAlert').remove();
                var root = $('body').first();
                var modalContainer = $('<div class="modal fade containerAlert" />').appendTo(root).show();
                var alertButtons = $('<div class="alert-btn" />')
                        .append($('<a href="#" />').addClass('btn btn-sm btn-info').text('Ok').on('click', function () {
                            if (typeof opt.okFn == "function") {
                                opt.okFn();
                            }
                            modalContainer.slideUp(150, function () {
                                $(this).remove();
                            });
                        }));
                if (opt.cancelable)
                    alertButtons.append($('<a href="#" />').addClass('btn btn-sm btn-danger').text('Cancel').on('click', function () {
                        modalContainer.slideUp(150, function () {
                            $(this).remove();
                        });
                    }));
                $('<div class="modal-dialog" />')
                        .append($('<div class="modal-content" />')
                                .append($('<p />').html(txt))
                                .append(alertButtons)
                ).appendTo(modalContainer.addClass('in'));
            }

            function viewPrintList() {
                $('.containerPrint').remove();
                var root = $('body').first();
                var modalContainer = $('<div class="modal fade containerPrint" />').appendTo(root).show();
                var rows = records.find('tr:visible');
                var tbl = $('<table id="content2print"/>');
                var btns = $('<div class="alert-btn" />')
                        .append($('<a href="#" />').addClass('btn btn-sm btn-warning').text('Select All').on('click', function () {
                            selectElementContents(tbl.get(0));
                            return false;
                        }))
                      /*  .append($('<a href="#" />').addClass('btn btn-sm btn-warning').text('save').on('click', function () {
                          // window.open('data:application/vnd.ms-excel,' + $('#dvData').html());
                            window.open('data:application/vnd.ms-excel,'+document.documentElement.innerHTML);
    						e.preventDefault();
                            return false;
                        }))*/
                        .append($('<a href="#" />').addClass('btn btn-sm btn-info').text('Close').on('click', function () {
                            modalContainer.slideUp(150, function () {
                                $(this).remove();
                            });
                            return false;
                        }));
                rows.each(function () {
                    var row = $(this);
                    var pRow = $('<tr/>');
                    row.find('th,td').each(function () {
                        var _o = $(this), _i;
                        if (row.parent().prop('tagName') == "THEAD") {
                            _i = $('<th/>').text(_o.text());
                        } else {
                            _i = $('<td/>');
                            var _i2 = _o.find('input:visible').first();
                            _i.text((_i2.is(':checkbox')) ? (_i2.is(':checked') ? 'yes' : 'no') : ((_i2.length > 0) ? "" + _i2.val().replace(/(\d{2})\/(\d{2})\/(\d{4})/g,"$1/$2/$3.") : _o.text()));
                        }
                        pRow.append(_i);
                    });
                    tbl.append(pRow);
                });
                $('<div class="modal-dialog" />')
                        .append($('<div class="modal-content" />')
                                .append($('<div class="printView" id="dvData" />').html($('<h4>Export View</h4>')).append(tbl)
                                        .append($('<p />').text('Press Ctrl + C to copy selected items.')))
                                .append(btns)
                ).appendTo(modalContainer.addClass('in'));
                selectElementContents(tbl.get(0));
            }

            function selectElementContents(el) {
                var body = document.body, range, sel;
                if (document.createRange && window.getSelection) {
                    range = document.createRange();
                    sel = window.getSelection();
                    sel.removeAllRanges();
                    try {
                        range.selectNodeContents(el);
                        sel.addRange(range);
                    } catch (e) {
                        range.selectNode(el);
                        sel.addRange(range);
                    }
                } else if (body.createTextRange) {
                    range = body.createTextRange();
                    range.moveToElementText(el);
                    range.select();
                }
            }
        })(_$);
    });

    function makeMoney(n) {
        if (!n)return "0";
        return n.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
    }
    function parseMoney(n) {
        n = n + "";
        if (!n) return 0;
        return parseFloat(n.replace(/[^\d\.\- ]/g, '').trim());
    }
    function sumArray(arr) {
        var n = arr.length, sum = 0;
        while (n--)
            sum += parseMoney(arr[n]) || 0;
        return sum;
    }
    
     editModeStatus();
function editModeStatus(){
	var val =document.getElementById("multiuserError").value;
	if('error'==val){
	document.getElementById("multiuserError").value ='';
	alert("This action is not allowed since another user is editing this proposal or another proposal that is linked to this record.");
	}
}

function dffDateAlert(inception,expery,rowNum) {
	//var inception=document.retro1.incepDate.value;
	var date=new Date(inception);
	date.setFullYear(date.getFullYear()+1);
	date.setDate(date.getDate());
	var d;
	var m
	if(parseInt(date.getDate())<10) {
		d="0"+date.getDate();
	}else {
		d=date.getDate();
	}
	if(parseInt(date.getMonth()+1)==0) {
		m="12"
	} else if((parseInt(date.getMonth())+1)<10) {
		m="0"+(parseInt(date.getMonth())+1);
	}else {
		m=(parseInt(date.getMonth())+1);
	}
	var y=date.getFullYear();					
	//var expery=(document.retro1.expDate.value).split("/");					
	var oneDay = 24*60*60*1000;
	var firstDate=new Date(y,m-1,d)
	var secondDate=new Date(expery);	
	var diffDays = Math.round((secondDate.getTime()-firstDate.getTime()));	
	//if(diffDays>0){
	//answer = confirm('Expiry Date is More than One Year.Do You Wish to Continue?');
	//}else 
	if(diffDays<0){
	alert('Premium Transaction Date should be greater than or equal to Acceptance date of '+inception +' in Row Number '+rowNum);
	return false;
	}else{
	return  true;
	}
	
}
function funBack(){
var dueDate=document.getElementById("dueDate").value;
var productId=document.getElementById("productId").value;
if(productId=='1'){
document.postForm.action='${pageContext.request.contextPath}/autoFaculPortfolio.action?dueDate='+dueDate;
}else{
document.postForm.action='${pageContext.request.contextPath}/autoNonPropPortfolio.action?dueDate='+dueDate;
}
document.postForm.submit();
}
function reformatDate(dateStr) { 
	dArr = dateStr.split("/");
	return dArr[2]+ "/" +dArr[1]+ "/" +dArr[0]; 
}
</script>
</body>
</html>
