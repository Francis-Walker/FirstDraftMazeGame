public class Bullet extends Entity{
    public Bullet(int tile_x,int tile_y, char dir)
    {
            this.setTileX(tile_x);
            this.setTileY(tile_y);
            this.setType("Bullet");
            this.setImg();
            this.setFacing(dir);

    }





}
