package Entity;

//by Cyprian Siwy
public class Vector2D
{
    public float x,y;

    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    public Vector2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D( Vector2D v)
    {
        x = v.x;
        y = v.y;
    }

    public float magnitude()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize()
    {
        if(this != Vector2D.zero)
        {
            x /= this.magnitude();
            y /= this.magnitude();
        }
    }

    public static Vector2D add(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }
    
    public static Vector2D remove(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2D multiply(Vector2D v1, float c)
    {
        return new Vector2D(v1.x * c, v1.y * c);
    }

    public static Vector2D divade(Vector2D v1, float c)
    {
        return new Vector2D(v1.x / c, v1.y / c);
    }

    public static Vector2D up =     new Vector2D(0, -1);
    public static Vector2D down =   new Vector2D(0, 1);
    public static Vector2D left =   new Vector2D(-1, 0);
    public static Vector2D right =  new Vector2D(1, 0);
    public static Vector2D zero =   new Vector2D(0, 0);

}