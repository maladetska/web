<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Posts</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td><@c.userlink user /></td>
                    <td><@c.postslink user posts?filter(f -> f.userId == user.id)?size /></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>