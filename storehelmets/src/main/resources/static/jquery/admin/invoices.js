$( document ).ready(function() {
		$("#dangChoDuyet").click(function(){
			$(".home-page").html(`
				<div class="awaiting-confirmation">
			<br/>
					<h4 class="text-center text-white">Danh sách đơn đặt hàng đang chờ duyệt</h4>
					
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
					      <th scope="col"><input type="checkbox" id="selecctall"/> Accept All</li></th>
					      <th scope="col">Thông tin khách hàng</th>
					      <th scope="col">Thông tin giỏ hàng</th>
					      <th scope="col">Email</th>
					      <th scope="col">Ngày tạo</th>
					      <th scope="col">Số điện thoại</th>
					      <th scope="col">Duyệt</th>
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
		});
		$('#dangChoDuyet').click(function(){
			$.ajax({
				url: "/api/v1/admins/invoices/getbykey/loadAtStart",
				method:"GET",
				contentType: "application/json",
				dataType: 'json',
			 })
			 .done(function( data ) {

				for(var i = 0; i<data.content.length; i++){
					$("#idTableInvoice").append(
						`"<tr>
				 			   <th scope="row">`+(i+1)+`</th>
							   <td><button class="btn btn-success" onclick=viewDetailCustomer("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
							   <td><button class="btn btn-danger" onclick= viewDetailBasket("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
							   <td >`+data.content[i].email+`</td>
							   <td >`+data.content[i].dateCreat+`</td>
							   <td >`+data.content[i].tel+`</td>
							   <td class="btn ml-2"><i class="fas fa-check" onclick=confirmBill("`+data.content[i].idInvoice+`")></i></td>
							 </tr>"`
						);
				}
				
			 });
			
		});

	});
function nextPages(key){
	$.ajax({
		url: "/api/v1/admins/invoices/getbykey/"+key,
		method:"GET",
		contentType: "application/json",
		dataType: 'json',
	 })
	 .done(function( data ) {
		 $('#idTableInvoice').html("");
		for(var i = 0; i<data.content.length; i++){
			$("#idTableInvoice").append(
				`"<tr>
		 			   <th scope="row">`+(i+1)+`</th>
					   <td><button class="btn btn-success" onclick=viewDetailCustomer("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
					   <td><button class="btn btn-danger" onclick=viewDetailBasket("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
					   <td >`+data.content[i].email+`</td>
					   <td >`+data.content[i].dateCreat+`</td>
					   <td >`+data.content[i].tel+`</td>
					   <td class="btn ml-2"><i class="fas fa-check" onclick=confirmBill("`+data.content[i].idInvoice+`")></i></td>
					 </tr>"`
				);
		}
		
	 });
	
}
function nextPagesInvoiceConfirm(key){
	$.ajax({
		url: "/api/v1/admins/invoices/awaitingapproval/"+key,
		method:"GET",
		contentType: "application/json",
		dataType: 'json',
	 })
	 .done(function( data ) {
		 $('#idTableInvoice').html("");
		for(var i = 0; i<data.content.length; i++){
			$("#idTableInvoice").append(
				`"<tr>
		 			   <th scope="row">`+(i+1)+`</th>
					   <td><button class="btn btn-success" onclick=viewDetailCustomer("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
					   <td><button class="btn btn-danger" onclick=viewDetailBasket("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
					   <td >`+data.content[i].email+`</td>
					   <td >`+data.content[i].dateCreat+`</td>
					   <td >`+data.content[i].tel+`</td>
					   <td class="btn ml-2"><i class="fas fa-check" onclick=confirmBill("`+data.content[i].idInvoice+`")></i></td>
					 </tr>"`
				);
		}
		
	 });
	
}
 function viewDetailCustomer(id){
	 if(id == null){
		 return;
	 }
	 var request =	 $.ajax({
			url: "/api/v1/admins/invoices/"+id,
			method:"GET",
			contentType: "application/json",
			dataType: 'json',
		 });
		 request.done(function( msg ) {
			$(".viewDetails").css("display", "block");
			$(".home-page").css("opacity","0.2");
			$(".viewDetails").html(`
				<div>
					 <div style="font-size: 50px;margin-left: 400px;margin-top: -20px;" class="icon" onclick="remove()"> <i class="fas fa-minus-circle"></i></div>
					 <label for="nameCustomer" class="">Họ tên khách hàng</label>
			    	 <input class="form-control col-md-6"  id="nameCustomer" value=`+msg.nameCustomer+`>
			    	  <label for="countryCustomer" >Tỉnh / Thành phố</label>
			    	 <input class="form-control col-md-6"  id="countryCustomer" value=`+msg.address1+`>
			    	  <label for="addressCustomer" >Địa chỉ</label>
			    	 <input class="form-control col-md-6"  id="addressCustomer" value=`+msg.address2+`>
			    	 <label for="telCustomer" >Số điện thoại</label>
			    	 <input class="form-control col-md-6"  id="telCustomer" value=`+msg.tel+`>
			    	 <label for="emailCustomer" >Email</label>
			    	 <input class="form-control col-md-6"  id="emailCustomer" value=`+msg.email+`>
				</div>
			`);
			
		 });
		 request.fail(function( jqXHR, textStatus ) {
			 alert( "Request failed: " + textStatus );
		 });
		 
 }
 
 function viewDetailBasket(id){
	 var request =	 $.ajax({
			url: "/api/v1/admins/invoices/"+id,
			method:"GET",
			contentType: "application/json",
			dataType: 'json',
		 });
		 request.done(function( msg ) {
			 var bastket = msg.bastketTotal.baskets;
			 console.log(msg);
			$(".viewDetails").css("display", "block");
			$(".home-page").css("opacity","0.1");
			$(".viewDetails").css("margin-left","-300px");
			$(".viewDetails").css("width","100%");
			$(".viewDetails").html(`
				<div>
					<div style="font-size: 50px;margin-left: 800px" class="icon" onclick="remove()"> <i class="fas fa-minus-circle"></i></div>
					<p class="text-center h3" style="padding-bottom: 80px;">Thông tin giỏ hàng</p>
					
					<table class="table">
					  <thead>
					    <tr>
					      <th scope="col">#</th>
					      <th scope="col">Tên sản phẩm</th>
					      <th scope="col">Hình ảnh</th>
					      <th scope="col">Giá tiền</th>
					      <th scope="col">Số lượng</th>
					      <th scope="col">Tổng số tiền</th>
					    </tr>
					  </thead>
					  <tbody id="bodyViewDetailsProducts">
					   
					  </tbody>
					</table>
					<div class="float-right mt-5" >
						<p style="padding-left:40px;" class="h4 ">Tổng số tiền</p>
						<br/>
						<p style="padding-left:40px;" class="h4 ">`+formatNumber(msg.bastketTotal.totalMoneyBasket,'-',',')+`đ</p>
					</div>
				</div>
				
			`);
			
			for(i = 0; i<bastket.length;i++){
				$("#bodyViewDetailsProducts").append(
					`
						<tr>
					      <td scope="row">`+i+`</td>
					      <td>`+bastket[i].nameProduct+`</td>
					      <td><img src="`+bastket[i].imageProduct+`" class="img-fluid" style="width:60px;"></td>
					      <td>`+formatNumber(bastket[i].price,'-',',')+` đ</td>
					      <td>`+bastket[i].numOfCart+`</td>
					      <td>`+formatNumber(bastket[i].totalMoney,'-',',')+`đ</td>
					    </tr>
					`		
				);
			}
		 });
		 request.fail(function( jqXHR, textStatus ) {
			 alert( "Request failed: " + textStatus );
		 });
 }
 function confirmBill(id){

	 var request =   $.ajax({
		  url: "/api/v1/users/auth/me",
		  contentType: "application/json",
		  methodType:"GET",
		  method:"GET"
	});
	request.done(function( data ) {
		if(data.idUser != null){
			 var request =   $.ajax({
				  url: "/api/v1/admins/invoices/"+data.idUser +"/"+id,
				  contentType: "application/json",
				  methodType:"PUT",
				  method:"PUT"
			});
			request.done(function( result ) {
				alert(result);
			});
			request.fail(function( jqXHR, textStatus ) {
			
			});	
		}
	});
	request.fail(function( jqXHR, textStatus ) {
	
	});	
	 
 }
 $("#daDuyet").click(function(){
	 $(".home-page").html(``);
	 $(".home-page").html(`
				<div class="awaiting-confirmation">
			<br/>
				<h4 class="text-center text-white" style="padding-bottom: 80px;">Danh sách đơn đặt hàng đã  duyệt</h4>
					<div class="row">
						<div class="col-md-6 pl-2" style="margin-top: -30px;">
							<div style="padding-left: 32px;">
									<label for="cars " >Lựa chọn tài xế:</label>
									<select id="idShipper" class="form-control col-md-6">
									 
									</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="search float-right col-md-6">
								<div class="row  no-gutters">
									<input type="date" class="col-md-10 form-control" >
									<button class="btn col-md-2 btn-secondary" style="border-radius:0"><i class="fas fa-search"></i></button>
								</div>
							</div>
						</div>
					</div>
					<br/>
					<br/>
					<br/>
					<table class="table  table-striped">
					  <thead>
					    <tr>
					      <th scope="col"><input type="checkbox" id="selecctall"/> Accept All</li></th>
					      <th scope="col">Thông tin khách hàng</th>
					      <th scope="col">Thông tin giỏ hàng</th>
					      <th scope="col">Email</th>
					      <th scope="col">Ngày tạo</th>
					      <th scope="col">Số điện thoại</th>
					      <th scope="col">Chuyển cho tài xế</th>
					    </tr>
					  </thead>
					  <tbody id="idTableInvoice">
				
					  </tbody>
					</table>
					<br/>
					<div class="btn btn-primary float-right mr-5" onclick="createInvoice()">Tạo hóa đơn</div>
					<br/>
					<br/>
					<div id="loadPages float-right ml-5 mt-5" style="padding-left: 490px;">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
							    <li class="page-item"><a class="page-link" onclick="nextPagesInvoiceConfirm('previousPageable')" href="#" >Previous</a></li>
							   
							    <li class="page-item"><p class="page-link" onclick="nextPagesInvoiceConfirm('nextPageable')">Next</p></li>
							</ul>
						</nav>		
					</div>
					
				</div>		
			`);
	 loadShipper();
	 $.ajax({
			url: "/api/v1/admins/invoices/awaitingapproval/loadAtStart",
			method:"GET",
			contentType: "application/json",
			dataType: 'json',
		 })
		 .done(function( data ) {
			for(var i = 0; i<data.content.length; i++){
				$("#idTableInvoice").append(
					`"<tr class="tr`+i+`">
			 			   <th scope="row">`+(i+1)+`</th>
						   <td><button class="btn btn-success" onclick=viewDetailCustomer("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
						   <td><button class="btn btn-danger" onclick= viewDetailBasket("`+data.content[i].idInvoice+`")>Xem chi tiết</button></td>
						   <td >`+data.content[i].email+`</td>
						   <td >`+data.content[i].dateCreat+`</td>
						   <td >`+data.content[i].tel+`</td>
						   <td class="btn ml-2"><input onclick=giaoBillChoShipper("`+data.content[i].idInvoice+`","`+i+`") class="btn btn-primary" type="button" value="Xác nhận"></td>
						 </tr>"`
					);
			}
			
		 });
 });
 
 function createInvoice(){
	 debugger;
	 var idShipper =	$( "#idShipper" ).val();
	 if(idShipper == null){
		 return;
	 }
	 var invoicess = JSON.parse(sessionStorage.getItem("invoices")); 
	 var invoiceObjects = [];
	 for(i = 0;i<invoicess.length;i++){
		 var invoiceObject = {"idInvoice":invoicess[i]}
		 invoiceObjects.push(invoiceObject);
	 } 
	 
	 JSON.stringify(invoiceObjects)
	 
	  var request =   $.ajax({
		   url: "/api/v1/users/auth/me",
		   contentType: "application/json",
		   methodType:"GET",
		   method:"GET"
		});
		request.done(function( data ) {
			console.log(data);
			var request =   
				$.ajax({
				 url: "/api/v1/admins/shippings/"+idShipper+"/"+data.idUser,
				 contentType: "application/json",
				 dataType: "application/json",
				 method:"POST",
				 data:JSON.stringify(invoiceObjects),
			});
			request.done(function( data ) {
				console.log(data);
			});
			request.fail(function( jqXHR, textStatus ) {
				console.log(textStatus);
			});	
		});
		request.fail(function( jqXHR, textStatus ) {
			console.log(textStatus);
		});	
		   
 }
 
 function giaoBillChoShipper(id,index){
	 debugger;
	 $(".tr"+index).css("background", "yellow");
	 $(".tr"+index).find('input').attr("disabled", "disabled");
	 let invoices = JSON.parse(sessionStorage.getItem("invoices"));
	 if(invoices == null){
		 var invoicess = [];	
		 invoicess.push(id);
		 sessionStorage.setItem("invoices", JSON.stringify(invoicess));
	 }else{
		 for(i=0;i<invoices.length;i++){
			 if(invoices[i]==id){
				 return;
			 }
		 }
		 invoicess = JSON.parse(sessionStorage.getItem("invoices"));
		 invoicess.push(id);
		 sessionStorage.setItem("invoices", JSON.stringify(invoicess))
	 }	 
	
 }
 removeSessionStorage();
 function removeSessionStorage(){
	 sessionStorage.removeItem("invoices");
 }
 
 
 function remove(){
	 $(".viewDetails").css("display", "none"); 
		$(".home-page").css("opacity","1");
 }

 function loadShipper(){
	$.ajax({
		url: "/api/v1/admins/shippings/drivers/shippers",
		method:"GET",
		contentType: "application/json",
		dataType: 'json',
	})
	.done(function( data ) {
		for(i=0;i<data.length;i++){
			$("#idShipper").append(`"<option value=`+data[i].idUser+`>`+data[i].fullName+`</option>"`)
		}
	}); 
 }
$('#dangVanChuyen').click(function(){
	$(".home-page").html(``);
	$(".home-page").html(`
			<div class="awaiting-confirmation">
		<br/>
			<h4 class="text-center text-white" style="padding-bottom: 40px;">Đơn đặt hàng đang vận chuyển</h4>
				<div class="row">
					<div class="col-md-6" style="margin-top: -20px;">
						
					</div>
					<div class="col-md-6">
						<div class="search float-right col-md-6">
							<div class="row  no-gutters">
								<label class="text-white">Tìm kiếm khách hàng</label><br/>
								<input class="form-control "/>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<br/>
				<table class="table container  table-striped">
				  <thead>
				    <tr>
				      <th scope="col">Họ tên khách hàng</th>
				      <th scope="col">Thông tin giỏ hàng</th>
				      <th scope="col">Ngày xác nhận hóa đơn</th>
				      <th scope="col">Nhân viên giao hàng</th>
				      <th scope="col">Admin xác nhận</th>
				    </tr>
				  </thead>
				  <tbody id="idTableInvoice">
			
				  </tbody>
				</table>
				
			</div>		
		`);
	getInvoiceStatusTransportIsTrue();	
});

 function getInvoiceStatusTransportIsTrue(){
	 $.ajax({
			url: "/api/v1/admins/invoices/beingtransported",
			method:"GET",
			contentType: "application/json",
			dataType: 'json',
		 })
		 .done(function( data ) {
			 $("#idTableInvoice").html(``);
			for(i=0;i<data.length;i++){
				$("#idTableInvoice").append(
					`"<tr class="tr`+i+`">
			 			 <td>`+data[i].nameCustomer+`</td>
						 <td><button class="btn btn-danger" onclick= viewDetailBasket("`+data[i].idInvoice+`")>Xem chi tiết</button></td>
						 <td>`+data[i].dateConfirm+`</td>
						 <td><button class="btn btn-warning"onclick=viewDetailCustomer("`+data[i].idInvoice+`")>Xem chi tiết</button></td>
						 <td><button class="btn btn-success" onclick=viewDetailAdmin("`+data[i].userConfirm.idUser+`")>Xem chi tiết</button></td>
					</tr>"`	
				);
			}
		 }); 
 }

 function viewDetailAdmin(id){
	 if(id == null){
		 return;
	 }
	 var request =	 $.ajax({
			url: "/api/v1/users/"+id,
			method:"GET",
			contentType: "application/json",
			dataType: 'json',
		 });
		 request.done(function( msg ) {
			$(".viewDetails").css("display", "block");
			$(".home-page").css("opacity","0.2");
			$(".viewDetails").html(`
				<div>
					 <div style="font-size: 50px;margin-left: 400px;margin-top: -20px;" class="icon" onclick="remove()"> <i class="fas fa-minus-circle"></i></div>
					 <label for="nameCustomer" class="">Họ tên</label>
			    	 <input class="form-control col-md-6"  id="nameCustomer" value=`+msg.nameCustomer+`>
			    	  <label for="countryCustomer" >Tỉnh / Thành phố</label>
			    	 <input class="form-control col-md-6"  id="countryCustomer" value=`+msg.address1+`>
			    	  <label for="addressCustomer" >Địa chỉ</label>
			    	 <input class="form-control col-md-6"  id="addressCustomer" value=`+msg.address2+`>
			    	 <label for="telCustomer" >Số điện thoại</label>
			    	 <input class="form-control col-md-6"  id="telCustomer" value=`+msg.tel+`>
			    	 <label for="emailCustomer" >Email</label>
			    	 <input class="form-control col-md-6"  id="emailCustomer" value=`+msg.email+`>
				</div>
			`);
			
		 });
		 request.fail(function( jqXHR, textStatus ) {
			 alert( "Request failed: " + textStatus );
		 });
}
