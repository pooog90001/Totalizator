package by.epam.litvin.util;

import by.epam.litvin.type.RouteType;

public class Router {
    private RouteType routeType = RouteType.FORWARD;

    private String routPath;

    public Router() {};

    public Router(RouteType routeType, String routPath) {
        this.routeType = routeType;
        this.routPath = routPath;
    }

    public Router(String routPath) {
        this.routPath = routPath;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        if (routeType == null) {
            this.routeType = RouteType.FORWARD;
        }
        this.routeType = routeType;
    }

    public String getRoutePath() {
        return routPath;
    }

    public void setRoutePath(String routPath) {
        this.routPath = routPath;
    }
}
