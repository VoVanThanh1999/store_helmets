	hienThiNhungHoaDonVanChuyen();
	function hienThiNhungHoaDonVanChuyen(){
		 var request =   $.ajax({
			  url: "/api/v1/users/auth/me",
			  contentType: "application/json",
			  methodType:"GET",
			  method:"GET"
		});
		request.done(function( data ) {
			if(data.idUser != null){
				var request1 = $.ajax({
					url: "/api/v1/shippers/shippingbills/"+data.idUser,
					method:"GET",
					contentType: "application/json",
				 });
				request1.done(function( msg ) {
					for(i=0;i<msg.length;i++){
						if(msg[i].totalMoneyNotYetCollected==0){
							$("#loadTable").append(`
								<tr>
								  <td>`+msg[i].idShippingBill+`</td>
								  <td>`+msg[i].totalMoneyInvoice+`</td>
								  <td>`+msg[i].totalMoneyCollected+`</td>
								  <td>`+msg[i].totalMoneyNotYetCollected+`</td>
								  <td>`+msg[i].tienDaHuy+`</td>
								  <td class="btn btn-danger"><a href=/shipper/chitiethoadon/idhoadon?idhoadon=`+msg[i].idShippingBill+` class="btn text-white">Xem chi tiết</a></td>
								  <td><p onclick=chuyenHoaDonChoAdmin("`+msg[i].idShippingBill+`")  class="btn btn-success"><i class="fas fa-check"></i></p></td>
							  	</tr>	  												
							`);
						}else{
							$("#loadTable").append(`
								<tr>
								  <td>`+msg[i].idShippingBill+`</td>
								  <td>`+msg[i].totalMoneyInvoice+`</td>
								  <td>`+msg[i].totalMoneyCollected+`</td>
								  <td>`+msg[i].totalMoneyNotYetCollected+`</td>
								  <td>`+msg[i].tienDaHuy+`</td>
								  <td class="btn btn-danger"><a href=/shipper/chitiethoadon/idhoadon?idhoadon=`+msg[i].idShippingBill+` class="btn text-white">Xem chi tiết</a></td>
								  <td><p class="btn btn-secondary" disable="disable"><i class="fas fa-check"></i></p></td>
							  	</tr>	  												
							`);
						}
						
					}
					
				 });
				request1.fail(function( jqXHR, textStatus ) {
		
				}); 
			}
		});
		request.fail(function( jqXHR, textStatus ) {
			alert(textStatus);
		});	
	}
	function chuyenHoaDonChoAdmin(idShipping){
		debugger;
		 var request =	 $.ajax({
				url: "/api/v1/shippers/shippingbills/"+idShipping,
				method:"PUT",
			 });
			 request.done(function( msg ) {
				 alert(msg);
			 });
			 request.fail(function( jqXHR, textStatus ) {
				 alert( "Request failed: " + textStatus );
			 });
	}
	hienThiHoaDonDangChoXacNhan();
	function hienThiHoaDonDangChoXacNhan(){
		 var request =   $.ajax({
			  url: "/api/v1/users/auth/me",
			  contentType: "application/json",
			  methodType:"GET",
			  method:"GET"
		});
		request.done(function( data ) {
			if(data.idUser != null){
				var request1 = $.ajax({
					url: "/api/v1/shippers/shippingbills/awaitingconfirmation",
					method:"GET",
					contentType: "application/json",
				 });
				request1.done(function( msg ) {
					for(i=0;i<msg.length;i++){
						$("#loadTable-dangChoXacNhan").append(
							`
								<tr>
									<td>`+msg[i].idShippingBill+`</td>
									<td>`+msg[i].totalMoneyInvoice+`</td>
									<td>`+msg[i].totalMoneyCollected+`</td>
									<td>`+msg[i].totalMoneyNotYetCollected+`</td>
									<td>`+msg[i].tienDaHuy+`</td>
									<td class="btn btn-danger"><a href=/shipper/chitiethoadon/idhoadon?idhoadon=`+msg[i].idShippingBill+` class="btn text-white">Xem chi tiết</a></td>
									<td>`+msg[i].ngayHoanThanh+`</td>
									
									
								</tr>
							`		
						);
					}
				});
				request1.fail(function( jqXHR, textStatus ) {
		
				}); 
			}
		});
		request.fail(function( jqXHR, textStatus ) {
			alert(textStatus);
		});	
	}

	hienThiHoaDonDaGiaoThanhCongTuShipper();
	function hienThiHoaDonDaGiaoThanhCongTuShipper(){
		debugger;
		var request =   $.ajax({
			  url: "/api/v1/users/auth/me",
			  contentType: "application/json",
			  methodType:"GET",
			  method:"GET"
		});
		request.done(function( data ) {
			if(data.idUser != null){
				 var request =   $.ajax({
					  url: "/api/v1/shippers/shippingbills/shippers/"+data.idUser,
					  contentType: "application/json",
					  methodType:"GET",
					  method:"GET"
				});
				request.done(function( result ) {
					for(var i=0;i<result.length;i++){
						$("#loadTable-daGiaoThanhCong").append(
							`
								<tr>
									<td>`+result[i].idShippingBill+`</td>
									<td>`+result[i].totalMoneyInvoice+`</td>
									<td>`+result[i].totalMoneyCollected+`</td>
									<td>`+result[i].tienDaHuy+`</td>
									<td><a href=/shipper/chitiethoadon/idhoadon?idhoadon=`+result[i].idShippingBill+` class="btn btn-danger">Xem chi tiet</a></td>
									<td>`+result[i].ngayHoanThanh+`</td>
									<td>`+result[i].ngayAdminXacNhanThanhCong+`</td>
								</tr>
							`);
					}
				});
				request.fail(function( jqXHR, textStatus ) {
				
				});	
			}
		});
		request.fail(function( jqXHR, textStatus ) {
		
		});	
	
	}

