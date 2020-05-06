package pucrs.br.structures;

public class Path {

    public int x;
    public int y;

    public Path(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + this.x+ ", " + this.y + "]";
    }
}