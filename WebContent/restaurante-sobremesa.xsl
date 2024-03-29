<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output encoding="UTF-8" indent="yes" method="html" />

	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<link href="styles/android.css" rel="stylesheet" type="text/css"
					id="docstyle" />
				<title>Cardápio do Bandejão</title>
				<script type="text/javascript" src="changeStyle.js"/>
			</head>
			<body>
				<header>
					<h1 id="title">Cardápio do Restaurante Universitário : Sobremesas</h1>
					<ul class="home">
						<li id="noStyle" onclick="changeStyle(none)">
							<a ref="index.html">
								<span class="en" style="display: inline">Sem estilo</span>
							</a>
						</li>
						<li id="androidStyle" onclick="changeStyle(android)">
							<a ref="index.html">
								<span class="en" style="display: inline">Android</span>
							</a>
						</li>
						<li id="atmosphereStyle" onclick="changeStyle(atmosphere)">
							<a ref="index.html">
								<span class="en" style="display: inline">Atmosphere</span>
							</a>
						</li>
						<li id="greenZapStyle" onclick="changeStyle(greenZap)">
							<a ref="index.html">
								<span class="en" style="display: inline">GreenZap</span>
							</a>
						</li>
					</ul>
				</header>

				<xsl:for-each select="restaurante/*">

					<div id="weekDayTitleFrame">
						<h3 id="weekDayTitleText">
							<xsl:choose>
								<xsl:when test="name()='segunda'">
									Segunda-feira
								</xsl:when>
								<xsl:when test="name()='terca'">
									Terça-feira
								</xsl:when>
								<xsl:when test="name()='quarta'">
									Quarta-feira
								</xsl:when>
								<xsl:when test="name()='quinta'">
									Quinta-feira
								</xsl:when>
								<xsl:when test="name()='sexta'">
									Sexta-feira
								</xsl:when>
								<xsl:when test="name()='sabado'">
									Sábado
								</xsl:when>
							</xsl:choose>
							(
							<xsl:value-of select="data" />
							)
						</h3>
					</div>
					<div id="weekDayContentFrame">
						<div id="weekDayContentText">
							<p>
								<b>
									<center>
										<h4> Almoço: </h4>
									</center>
								</b>
							</p>

							<xsl:for-each select="almoco">
								<xsl:choose>
									<xsl:when test="vazio">
										<p>
											<center>
												<i>Não serão servidas refeições.</i>
											</center>
										</p>
									</xsl:when>
									<xsl:otherwise>
										<p>
											<center>
											<xsl:value-of select="sobremesa" />
											.</center>
										</p>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>

							<p>
								<b>
									<center>
										<h4> Jantar: </h4>
									</center>
								</b>
							</p>

							<xsl:for-each select="jantar">
								<xsl:choose>
									<xsl:when test="vazio">
										<p>
											<center>
												<i>Não serão servidas refeições.</i>
											</center>
										</p>
									</xsl:when>
									<xsl:otherwise>
										<p>
											<center>
											<xsl:value-of select="sobremesa" />
											.</center>
										</p>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
						</div>
					</div>
					<xsl:if test="name()!='sabado'">
						<div id="spacer"></div>
					</xsl:if>
				</xsl:for-each>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>