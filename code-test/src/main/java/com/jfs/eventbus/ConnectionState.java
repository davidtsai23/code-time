package com.jfs.eventbus;

public enum ConnectionState {

    /**
     * Indicates the portal manager is trying to connect to the remote portal.
     */
    CONNECTING,
    /**
     * Indicates the portal is connected and logged in to a remote portal, thus in a usable state.
     */
    CONNECTED,
    /**
     * Indicates the portal is disconnecting from the remote portal.
     */
    DISCONNECTING,
    /**
     * Indicates the portal is not connected to a remote portal.
     * While in this state, calls to the portal's service methods will result in a
     * {@link ServiceUnavailableException}.
     */
    DISCONNECTED;
}
