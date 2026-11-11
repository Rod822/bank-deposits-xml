<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <!-- вклади розбиваються на підсписки за типом вкладу -->
    <xsl:template match="/bank">
        <bank>
            <!-- беремо вклади, тип яких ще не зустрічався вище -->
            <xsl:for-each
                    select="deposit[not(type = preceding-sibling::deposit/type)]">
                <xsl:element name="{type}">
                    <xsl:apply-templates
                            select="../deposit[type = current()/type]"/>
                </xsl:element>
            </xsl:for-each>
        </bank>
    </xsl:template>

    <!-- вклад переписується без змін -->
    <xsl:template match="deposit">
        <deposit account-id="{@account-id}">
            <xsl:for-each select="*">
                <xsl:element name="{local-name()}">
                    <xsl:value-of select="."/>
                </xsl:element>
            </xsl:for-each>
        </deposit>
    </xsl:template>
</xsl:stylesheet>
