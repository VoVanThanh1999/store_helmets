$( document ).ready(function() {
	 $(".home-page").html(``);
   $("#hoaDonDangVanChuyen").click(function(){
	   $(".home-page").html(`
				<div class="awaiting-confirmation">
			<br/>
					<h4 class="text-center text-white">Danh sách Biên lai đang vận chuyển</h4>
					
					<div class="search float-right col-md-3">
						<div class="row  no-gutters">
							<input type="date" class="col-md-10 form-control" >
							<button class="btn col-md-2 btn-secondary" style="border-radius:0"><i class="fas fa-search"></i></button>
						</div>
					</div>
					<br/>
					<br/>
					<br/>
					<table class="table  table-striped">
					  <thead>
					    <tr>
					      <th scope="col">Mã đơn hàng</th>
					      <th scope="col">Danh sách đơn đạt hàng</th>
					      <th scope="col">Tổng tiền hóa đơn</th>
					      <th scope="col">Ngày tạo</th>
					      <th scope="col">Shipper </th>
					      <th scope="col">Người xác nhận</th>
					    </tr>
					  </thead>
					  <tbody id="idTableInvoice">
				
					  </tbody>
					</table>
					<br/>
					<div id="loadPages float-right ml-5 mt-5" style="padding-left: 490px;">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
							    <li class="page-item"><a class="page-link" onclick="nextPages('previousPageable')" href="#" >Previous</a></li>
							   
							    <li class="page-item"><p class="page-link" onclick="nextPages('nextPageable')">Next</p></li>
							</ul>
						</nav>		
					</div>
					
				</div>		
			`);	
	   loadShippingBill();
   }) ;
   function loadShippingBill(){
	   var request = $.ajax({
		   url: "/api/v1/admins/shippings/beingtransported",
		   method: "GET",
		 });
		  
		 request.done(function( msg ) {
			 for(i=0;i<msg.length;i++){
				 $("#idTableInvoice").append(
					`
						<tr>
						  <td scope="row">`+msg[i].idShippingBill+`</td>
					      <td scope="row"><button class="btn btn-success">Xem chi tiết</button></td>
					      <td>`+formatNumber(msg[i].totalMoneyInvoice,'-',',')+` đ</td>
					      <td>`+msg[i].date+`</td>
					      <td><button class="btn btn-danger" 	onclick=viewDetailAdmin("`+msg[i].shipper.idUser+`")>Xem chi tiết</button></td>
					      <td><button class="btn btn-secondary" onclick=viewDetailAdmin("`+msg[i].adminCreate.idUser+`")>Xem chi tiết</button></td>
					    </tr>
					`
				 ); 
			 }
		 });
		  
		 request.fail(function( jqXHR, textStatus ) {
		   alert( "Request failed: " + textStatus );
		 });
   }
});