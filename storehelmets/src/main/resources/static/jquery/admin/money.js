$( document ).ready(function() {
	$('#tienDangChoLay').click(function(){
		$(".home-page").html(`
				<div class="awaiting-confirmation">
			<br/><br/>
					<h4 class="text-center text-white">Danh sách chi tiết số tiền đang vận chuyển</h4>
						<div>
							<p class="text-center h6 text-white">Tiền đang chờ lấy</p>
							<br/>
							<div class="row float-right  no-gutters col-md-3">
								<input type="date" class="col-md-10 form-control" >
								<button class="btn col-md-2 btn-secondary" style="border-radius:0"><i class="fas fa-search"></i></button>
							</div>
							<div class="row float-left  no-gutters col-md-3">
								<p class=" btn">Tổng số tiền</p>
								</br>
								<p class="btn text-danger" id="tongTienDangCho"></p>
								
							</div>
							<br/>
							<table class="table table-bordered  table-striped" style="margin-top: 65px;">
							  <thead>
							    <tr>
							      <th scope="col">Thời gian </th>
							      <th scope="col">Tổng số tiền hóa đơn</th>
							      <th scope="col">Chi tiết hóa đơn</th>
							    </tr>
							  </thead>
							  <tbody id="IdTienDangChoLay">
						
							  </tbody>
							</table>
					<br/>
				</div>		
			`);	
		hienThiSoTienDangChoLay();
	});
	function hienThiSoTienDangChoLay(){
		   var request = $.ajax({
			   url: "/api/v1/admins/shippings/beingtransported",
			   method: "GET",
			 });
			 request.done(function( msg ) {
				 var totalMoney = 0;
				 for(i=0;i<msg.length;i++){
					 totalMoney+= msg[i].totalMoneyInvoice;
					 $("#IdTienDangChoLay").append(
						`
							<tr>
							  <td>`+msg[i].date+`</td>
							  <td>`+formatNumber(msg[i].totalMoneyInvoice,'-',',')+` đ</td>
						      <td scope="row"><button class="btn btn-success">Xem chi tiết</button></td>
						    </tr>
						`
					 ); 
				 }
				 var money = formatNumber(totalMoney,'-',',');
				 $("#tongTienDangCho").html(money+` đ`);
			 });
			  
			 request.fail(function( jqXHR, textStatus ) {
			   alert( "Request failed: " + textStatus );
			 });
	   }
	
});