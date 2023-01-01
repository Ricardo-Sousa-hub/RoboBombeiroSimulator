public class Render {
    private final int width;
    private final int height;
    private final int[] pixels;

    public Render(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }
}
