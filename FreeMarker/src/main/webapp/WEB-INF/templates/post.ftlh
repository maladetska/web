<#-- @ftlvariable name="post_id" type="long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if post_id??>
        <#assign post = c.findBy(posts, "id", post_id)!/>
        <#if post?has_content>
            <@c.postview post true/>
        <#else>
            No such post
        </#if>
    <#else>
        No such post
    </#if>
</@c.page>