package com.qlcx.framework.web.service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qlcx.common.utils.StringUtils;

/**
 * qlcx's first js call thymeleaf to realize button permission visibility
 */
@Service("permission")
public class PermissionService
{
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    /** No permission, hidden is used to hide buttons on the front end */
    public static final String NOACCESS = "hidden";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    /**
     * Verify whether the user has a certain permission, if no permission is returned, hidden is used for front-end hiding (if you need to return Boolean, use isPermitted)
     *
     * @param permission permission string
     * @return does the user have a certain permission
     */
    public String hasPermi(String permission)
    {
        return isPermitted(permission)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify that the user does not have certain permissions, contrary to hasPermi logic. No permission to return hidden for front-end hiding (if you need to return Boolean use isLacksPermitted)
     *
     * @param permission permission string
     * @return Whether the user does not have a certain permission
     */
    public String lacksPermi(String permission)
    {
        return isLacksPermitted(permission)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify that the user has any of the following permissions, if no permission is returned, hidden is used for hiding (if you need to return Boolean, use hasAnyPermissions)
     *
     * @param permissions list of permissions with PERMISSION_NAMES_DELIMETER as separator
     * @return does the user have any of the following permissions
     */
    public String hasAnyPermi(String permissions)
    {
        return hasAnyPermissions(permissions, PERMISSION_DELIMETER)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify whether the user has a certain role, if you don’t have permission to return hidden for hiding (if you need to return Boolean use isRole)
     *
     * @param role role string
     * @return Whether the user has a role
     */
    public String hasRole(String role)
    {
        return isRole(role)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify that the user does not have a role, contrary to hasRole logic. No permission to return hidden for hiding (if you need to return Boolean use isLacksRole)
     *
     * @param role role string
     * @return Whether the user does not have a role
     */
    public String lacksRole(String role)
    {
        return isLacksRole(role)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify that the user has any of the following roles. If you don’t have permission to return hidden to hide (if you need to return Boolean use isAnyRoles)
     *
     * @param roles List of roles with ROLE_NAMES_DELIMETER as separator
     * @return Whether the user has any of the following roles
     */
    public String hasAnyRoles(String roles)
    {
        return isAnyRoles(roles, ROLE_DELIMETER)? StringUtils.EMPTY: NOACCESS;
    }

    /**
     * Verify if the user is authenticated or remembered.
     *
     * @return Whether the user is authenticated or remembered
     */
    public boolean isUser()
    {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.getPrincipal() != null;
    }

    /**
     * Determine whether the user has a certain permission
     *
     * @param permission permission string
     * @return does the user have a certain permission
     */
    public boolean isPermitted(String permission)
    {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

    /**
     * Judge whether the user does not have a certain authority, contrary to the logic of isPermitted.
     *
     * @param permission permission name
     * @return Whether the user does not have a certain permission
     */
    public boolean isLacksPermitted(String permission)
    {
        return isPermitted(permission) != true;
    }

    /**
     * Verify that the user has any of the following permissions.
     *
     * @param permissions list of permissions with PERMISSION_NAMES_DELIMETER as separator
     * @return does the user have any of the following permissions
     */
    public boolean hasAnyPermissions(String permissions)
    {
        return hasAnyPermissions(permissions, PERMISSION_DELIMETER);
    }

    /**
     * Verify that the user has any of the following permissions.
     *
     * @param permissions list of permissions with delimeter as separator
     * @param delimeter permission list separator
     * @return does the user have any of the following permissions
     */
    public boolean hasAnyPermissions(String permissions, String delimeter)
    {
        Subject subject = SecurityUtils.getSubject();

        if (subject != null)
        {
            if (delimeter == null || delimeter.length() == 0)
            {
                delimeter = PERMISSION_DELIMETER;
            }

            for (String permission: permissions.split(delimeter))
            {
                if (permission != null && subject.isPermitted(permission.trim()) == true)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determine whether the user has a role
     *
     * @param role role string
     * @return Whether the user has a role
     */
    public boolean isRole(String role)
    {
        return SecurityUtils.getSubject().hasRole(role);
    }

    /**
     * Verify that the user does not have a role, contrary to the logic of isRole.
     *
     * @param role role name
     * @return Whether the user does not have a role
     */
    public boolean isLacksRole(String role)
    {
        return isRole(role) != true;
    }

    /**
     * Verify that the user has any of the following roles.
     *
     * @param roles List of roles with ROLE_NAMES_DELIMETER as separator
     * @return Whether the user has any of the following roles
     */
    public boolean isAnyRoles(String roles)
    {
        return isAnyRoles(roles, ROLE_DELIMETER);
    }

    /**
     * Verify that the user has any of the following roles.
     *
     * @param roles List of roles with delimeter as separator
     * @param delimeter character list separator
     * @return Whether the user has any of the following roles
     */
    public boolean isAnyRoles(String roles, String delimeter)
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            if (delimeter == null || delimeter.length() == 0)
            {
                delimeter = ROLE_DELIMETER;
            }

            for (String role : roles.split(delimeter))
            {
                if (subject.hasRole(role.trim()) == true)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
      * Return user attribute value
      *
      * @param property property name
      * @return user attribute value
      */
    public Object getPrincipalProperty(String property)
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            Object principal = subject.getPrincipal();
            try
            {
                BeanInfo bi = Introspector.getBeanInfo(principal.getClass());
                for (PropertyDescriptor pd : bi.getPropertyDescriptors())
                {
                    if (pd.getName().equals(property) == true)
                    {
                        return pd.getReadMethod().invoke(principal, (Object[]) null);
                    }
                }
            }
            catch (Exception e)
            {
                log.error("Error reading property [{}] from principal of type [{}]", property, principal.getClass().getName());
            }
        }
        return null;
    }
}
