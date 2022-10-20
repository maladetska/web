<#-- @ftlvariable name="user_id" type="long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <@c.postviews posts?filter(f -> f.userId == user_id)?reverse/>
    <#else>
        No such user
    </#if>
</@c.page>