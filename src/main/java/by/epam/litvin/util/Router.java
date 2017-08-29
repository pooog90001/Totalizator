package by.epam.litvin.util;

import by.epam.litvin.type.RouteType;

public class Router {
    private RouteType routeType = RouteType.FORWARD;

    private String routePath;

    /**
     * Default constructor.
     */
    public Router() {
    }

    /**
     * Constructor with route type and route path parameters.
     *
     * @param routeType
     * @param routePath
     */
    public Router(RouteType routeType, String routePath) {
        this.routeType = routeType;
        this.routePath = routePath;
    }

    /**
     * Constructor with route path parameters.
     *
     * @param routePath
     */
    public Router(String routePath) {
        this.routePath = routePath;
    }

    /**
     * Get route type. rout
     *
     * @return
     */
    public RouteType getRouteType() {
        return routeType;
    }

    /**
     * Set route type.
     *
     * @param routeType
     */
    public void setRouteType(RouteType routeType) {
        if (routeType == null) {
            this.routeType = RouteType.FORWARD;
        }
        this.routeType = routeType;
    }

    /**
     * Get route path.
     *
     * @return
     */
    public String getRoutePath() {
        return routePath;
    }

    /**
     * Set route path.
     *
     * @param routPath
     */
    public void setRoutePath(String routPath) {
        this.routePath = routePath;
    }
}
