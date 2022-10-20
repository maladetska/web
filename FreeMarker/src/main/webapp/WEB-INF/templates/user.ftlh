<#-- @ftlvariable name="user_id" type="long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign user_trio = c.find3By(users, "handle", handle)!/>
    <#elseif user_id??>
        <#assign user_trio = c.find3By(users, "id", user_id)!/>
    </#if>

    <#if user_trio?has_content>
        <div class="user_page">
            <div class="user_info">
                <#assign user = user_trio[1]/>
                <div class="name"><@c.userlink user /></div>
                <ul class="info">
                    <li>id: ${user.id}</li>
                    <li>handle: ${user.handle}</li>
                    <li>name: ${user.name}</li>
                    <li>posts: <@c.postslink user posts?filter(link -> link.userId == user.id)?size /></li>
                </ul>
            </div>
            <div class="photo">
                <img src="../../img/avatar.jpeg" alt="Avatar">
            </div>
        </div>
        <div class="arrows">
            <#macro userlink user textpointer>
                <#if user?has_content>
                    <a href="user?handle=${user.handle}">${textpointer}</a>
                <#else>
                    <a class="does_not_exist">${textpointer}</a>
                </#if>
            </#macro>

            <@userlink user_trio[0] '←'/>
            <@userlink user_trio[2] '→'/>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
