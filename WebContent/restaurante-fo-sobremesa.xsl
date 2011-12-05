<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<!-- generate PDF page structure -->
	<xsl:output encoding="UTF-8" indent="yes" method="xml" />
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="page"
					page-height="29.7cm" page-width="21cm" margin-top="1cm"
					margin-bottom="2cm" margin-left="2.5cm" margin-right="2.5cm">
					<fo:region-body margin-top="3cm" />
					<fo:region-before extent="3cm" />
					<fo:region-after extent="1.5cm" />
				</fo:simple-page-master>

				<fo:page-sequence-master master-name="run">
					<fo:repeatable-page-master-reference
						master-reference="page" />
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="run">
				
				<fo:static-content flow-name="xsl-region-before">
					<fo:block font-size="10pt" line-height="12pt"
							font-family="sans-serif" text-align="right">
							Página <fo:page-number/>
					</fo:block>
				</fo:static-content>
			
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-size="32pt" font-family="sans-serif"
						font-weight="bold" space-after.optimum="24pt" color="#94B922"
						text-align="center">
						Cardápio do Restaurante Universitário
					</fo:block>
					<fo:block>
						<xsl:apply-templates />
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template match="segunda|terca|quarta|quinta|sexta|sabado">
		<xsl:choose>
			<xsl:when test="name()='segunda'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Segunda-feira (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
			<xsl:when test="name()='terca'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Terça-feira (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
			<xsl:when test="name()='quarta'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Quarta-feira (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
			<xsl:when test="name()='quinta'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Quinta-feira (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
			<xsl:when test="name()='sexta'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Sexta-feira (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
			<xsl:when test="name()='sabado'">
				<fo:block color="#003048" font-size="18pt" text-align="center">
					Sábado (
					<xsl:value-of select="data" />
					)
				</fo:block>
			</xsl:when>
		</xsl:choose>
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="data" />

	<xsl:template match="almoco|jantar">
		<fo:block text-align="center" background-color="#EEEEEE"
			space-after.optimum="5pt" space-before.optimum="5pt">
			<fo:block color="#666" font-weight="bold" text-align="center"
				font-family="sans-serif">
				<xsl:if test="name()='almoco'">
					Almoço
				</xsl:if>
				<xsl:if test="name()='jantar'">
					Jantar
				</xsl:if>
			</fo:block>
			<xsl:apply-templates />
		</fo:block>
	</xsl:template>

	<xsl:template match="vazio">
		<fo:block color="#666" font-style="italic">
			Não serão servidas refeições.
		</fo:block>
	</xsl:template>

	<xsl:template match="salada"/>

	<xsl:template match="principal"/>

	<xsl:template match="acompanhamento"/>

	<xsl:template match="sobremesa">
		<fo:block color="#666">
			Sobremesa:
			<xsl:value-of select="current()" />.
		</fo:block>
	</xsl:template>

</xsl:stylesheet>
