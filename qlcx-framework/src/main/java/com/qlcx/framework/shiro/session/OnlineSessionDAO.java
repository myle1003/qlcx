package com.qlcx.framework.shiro.session;

import java.io.Serializable;
import java.util.Date;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.qlcx.common.enums.OnlineStatus;
import com.qlcx.framework.manager.AsyncManager;
import com.qlcx.framework.manager.factory.AsyncFactory;
import com.qlcx.framework.shiro.service.SysShiroService;

/**
 * Db operation for custom ShiroSession
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO
{
    /**
     * The period for synchronizing the session to the database is in milliseconds (default 1 minute)
     */
    @Value("${shiro.session.dbSyncPeriod}")
    private int dbSyncPeriod;

    /**
     * The timestamp of the last database synchronization
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    @Autowired
    private SysShiroService sysShiroService;

    public OnlineSessionDAO()
    {
        super();
    }

    public OnlineSessionDAO(long expireTime)
    {
        super();
    }

    /**
     * Get session based on session ID
     *
     * @param sessionId session ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        return sysShiroService.getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException
    {
        super.update(session);
    }

    /**
     * Update session; it will be called if update session last access time/stop session/set timeout time/set removal attribute etc.
     */
    public void syncToDb(OnlineSession onlineSession)
    {
        Date lastSyncTimestamp = (Date) onlineSession.getAttribute(LAST_SYNC_DB_TIMESTAMP);
        if (lastSyncTimestamp != null)
        {
            boolean needSync = true;
            long deltaTime = onlineSession.getLastAccessTime().getTime()-lastSyncTimestamp.getTime();
            if (deltaTime <dbSyncPeriod * 60 * 1000)
            {
                // Insufficient time difference No need to synchronize
                needSync = false;
            }
            // isGuest = true visitors
            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;

            // The session data has changed
            if (!isGuest == false && onlineSession.isAttributeChanged())
            {
                needSync = true;
            }

            if (!needSync)
            {
                return;
            }
        }
        // Update the last synchronization database time
        onlineSession.setAttribute(LAST_SYNC_DB_TIMESTAMP, onlineSession.getLastAccessTime());
        // Reset the logo after updating
        if (onlineSession.isAttributeChanged())
        {
            onlineSession.resetAttributeChanged();
        }
        AsyncManager.me().execute(AsyncFactory.syncSessionToDb(onlineSession));
    }

    /**
     * When the session expires/stops (such as when the user quits) the attribute will be called
     */
    @Override
    protected void doDelete(Session session)
    {
        OnlineSession onlineSession = (OnlineSession) session;
        if (null == onlineSession)
        {
            return;
        }
        onlineSession.setStatus(OnlineStatus.off_line);
        sysShiroService.deleteSession(onlineSession);
    }
}
