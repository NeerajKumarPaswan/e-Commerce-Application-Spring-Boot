<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Java Techie Mail</title>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top" bgcolor="silver"
				style="background-color: #silver;"><br> <br>
				<table width="600" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" valign="top" bgcolor="grey"
							style="background-color: grey; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">
							
							<div style="font-size: 48px; color:black;">
								<b>WELCOME TO SHOPPING-CART</b>
							</div>
							
							<div style="font-size: 20px; color: green;">
								<br> Congratulations ${merchant.getName()}
							</div>
							
							<div style="font-size: 24px; color: pink;">
								<br> Your Request for Registration as Merchant Has Been Received 
							</div>
							<div>
								<br> We're excited to have you get started . First, you need to confirm your account. Just press the button below and set the password.<br>
								</div>
								<div>
								<a href="http://localhost:8080/merchants/confirm?token=${merchant.getToken()}">click here</a>
								</div> <br>
								<div>
								<br> ""This website is created to share the experiance of Shopping by sitting in home"<br>
								<br>"Enjoy your day with Shopping " <br> <br>
								
								<br>
							</div>
						</td>
					</tr>
				</table> <br> <br></td>
		</tr>
	</table>
</body>
</html>