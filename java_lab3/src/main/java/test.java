
    interface Geometric {
        public double getPerimeter();
        public double getArea();
    }

    class Circl implements Geometric {
        // Protected variable
        /** WRITE YOUR CODE HERE **/
        protected double radius;
        // Constructor
        /** WRITE YOUR CODE HERE **/
        Circl(double radius){
            this.radius = radius;
        }

        // Implement toString method
        public String toString() {
            /**（保留一位小数）
             * output: Circle[radius=2.5]
             * */
            /** WRITE YOUR CODE HERE **/
            return String.format("Circle[radius=%.1f]", radius);
        }

        // Implement methods defined in the interface GeometricObject
        @Override
        public double getPerimeter() {
            /** WRITE YOUR CODE HERE **/
            return 2 * Math.PI * radius;
        }
        @Override
        public double getArea() {
            /** WRITE YOUR CODE HERE **/
            return Math.PI * radius * radius;
        }

    }

    interface Resizabl {
        public void resize(int percent);
    }

    class ResizableCircl extends Circl implements Resizabl {
        // Constructor
        /** WRITE YOUR CODE HERE **/
        ResizableCircl(double radius){
            super(radius);
        }

        // Reimplement toString method
        public String toString() {
            /**（保留一位小数）
             * output: ResizableCircle[Circle[radius=2.5]]
             * */
            /** WRITE YOUR CODE HERE **/
            return String.format("ResizableCircle[Circle[radius=%.1f]]", radius);
        }

        // Implement methods defined in the interface Resizable
        @Override
        public void resize(int percent) {
            radius = radius *  (percent / 100.0);
        }
    }
public class test{
    public static void main(String agrs[]){
        ResizableCircl s = new ResizableCircl(2.535);
    }
}
