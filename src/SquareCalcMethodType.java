public enum SquareCalcMethodType {
    LEFT((x, y) -> f(x) * (y - x)),
    RIGHT((x, y) -> f(y) * (y - x)),
    RANDOM((x, y) -> f(x + (y - x) * Math.random()) * (y - x)),
    TRAPEZOID((x, y) -> (f(x) + f(y)) * (y - x) / 2),
    SIMPSON((x, y) -> (f(x) + 4*f((x + y)/2) + f(y)) * (y - x) / 6);
    SquareCalcMethod method;
    SquareCalcMethodType(SquareCalcMethod method){
        this.method = method;
    }
    public SquareCalcMethod getMethod(){
        return method;
    }
    public static double f(double x){
        return 1/(x*x);
    }
}
