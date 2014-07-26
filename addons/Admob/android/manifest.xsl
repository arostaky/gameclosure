<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:android="http://schemas.android.com/apk/res/android">

	<xsl:param name="admobUnitid" />
	<xsl:param name="testDeviceid" />

    <xsl:output indent="yes" />
	<xsl:template match="comment()" />

	<xsl:template match="meta-data[@android:name='AD_UNIT_ID']">
		<meta-data android:name="AD_UNIT_ID" android:value="{$admobUnitid}"/>
	</xsl:template>

	<xsl:template match="meta-data[@android:name='TEST_DEVICE']">
		<meta-data android:name="TEST_DEVICE" android:value="{$testDeviceid}"/>
	</xsl:template>

	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>