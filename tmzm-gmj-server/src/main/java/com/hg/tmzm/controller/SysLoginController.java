package com.hg.tmzm.controller;

import com.hg.tmzm.common.response.AjaxResult;
import com.hg.tmzm.common.response.LoginResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录认证 Controller
 */
@RestController
public class SysLoginController extends BaseController {

    private static final Map<String, LoginUser> TOKEN_STORE = new ConcurrentHashMap<>();

    /**
     * 登录
     */
    @PostMapping("/login")
    public LoginResult login(@RequestBody Map<String, String> loginBody) {
        String username = loginBody.get("username");
        String password = loginBody.get("password");

        if (("admin".equals(username) && "admin123".equals(password))
                || ("hgm0".equals(username) && "hgm0".equals(password))) {
            String token = UUID.randomUUID().toString().replace("-", "");
            LoginUser user = new LoginUser();
            user.setUserId(1L);
            user.setUsername(username);
            user.setNickName("管理员");
            user.setRoles(Collections.singletonList("admin"));
            user.setPermissions(Collections.singletonList("*:*:*"));
            TOKEN_STORE.put(token, user);
            return LoginResult.success(token);
        }
        return LoginResult.error("用户名或密码错误");
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo(@RequestHeader(value = "Authorization", required = false) String authorization) {
        LoginUser user = getUserByToken(authorization);
        if (user == null) {
            return AjaxResult.error(401, "登录状态已过期");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("roles", user.getRoles());
        result.put("permissions", user.getPermissions());
        return success(result);
    }

    /**
     * 获取路由
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters(@RequestHeader(value = "Authorization", required = false) String authorization) {
        LoginUser user = getUserByToken(authorization);
        if (user == null) {
            return AjaxResult.error(401, "登录状态已过期");
        }

        List<Map<String, Object>> routers = new ArrayList<>();

        // 首页
        routers.add(buildMenu("首页", "index", "index", "HomeFilled", true));

        // 计划管理
        Map<String, Object> planMenu = buildParentMenu("计划管理", "plan", "Document", false);
        List<Map<String, Object>> planChildren = new ArrayList<>();
        planChildren.add(buildMenu("生产规格配置", "config", "tmzm/plan/config/index", "Setting", false));
        planChildren.add(buildMenu("计划排程", "schedule", "tmzm/plan/schedule/index", "Calendar", false));
        planChildren.add(buildMenu("匹配配置", "match", "tmzm/plan/match/index", "Connection", false));
        planMenu.put("children", planChildren);
        routers.add(planMenu);

        // 设备管理
        Map<String, Object> deviceMenu = buildParentMenu("设备管理", "device", "Monitor", false);
        List<Map<String, Object>> deviceChildren = new ArrayList<>();
        deviceChildren.add(buildMenu("定脱管机架", "frame", "tmzm/device/frame/index", "Box", false));
        deviceChildren.add(buildMenu("转鼓管理", "drum", "tmzm/device/drum/index", "SetUp", false));
        deviceChildren.add(buildMenu("穿孔辊管理", "punchroll", "tmzm/device/punchroll/index", "Cpu", false));
        deviceChildren.add(buildMenu("连轧机架/辊", "rolling", "tmzm/device/rolling/index", "Grid", false));
        deviceMenu.put("children", deviceChildren);
        routers.add(deviceMenu);

        return success(routers);
    }

    /**
     * 退出
     */
    @PostMapping("/logout")
    public AjaxResult logout(@RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            TOKEN_STORE.remove(token);
        }
        return success();
    }

    private LoginUser getUserByToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        String token = authorization.substring(7);
        return TOKEN_STORE.get(token);
    }

    private Map<String, Object> buildMenu(String name, String path, String component, String icon, boolean affix) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", capitalize(name));
        menu.put("path", path);
        menu.put("component", "Layout".equals(component) ? "Layout" : component);
        menu.put("hidden", false);

        Map<String, Object> meta = new HashMap<>();
        meta.put("title", name);
        meta.put("icon", icon);
        meta.put("affix", affix);
        menu.put("meta", meta);

        return menu;
    }

    private Map<String, Object> buildParentMenu(String name, String path, String icon, boolean affix) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", capitalize(name));
        menu.put("path", "/" + path);
        menu.put("component", "Layout");
        menu.put("hidden", false);
        menu.put("redirect", "noRedirect");

        Map<String, Object> meta = new HashMap<>();
        meta.put("title", name);
        meta.put("icon", icon);
        menu.put("meta", meta);

        menu.put("children", new ArrayList<>());
        return menu;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 内部用户类
     */
    public static class LoginUser {
        private Long userId;
        private String username;
        private String nickName;
        private String avatar = "";
        private List<String> roles;
        private List<String> permissions;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getNickName() { return nickName; }
        public void setNickName(String nickName) { this.nickName = nickName; }
        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
        public List<String> getPermissions() { return permissions; }
        public void setPermissions(List<String> permissions) { this.permissions = permissions; }

        // 兼容前端 user.userName
        public String getUserName() { return nickName; }
    }
}
