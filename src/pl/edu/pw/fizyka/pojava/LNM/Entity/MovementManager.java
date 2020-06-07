package pl.edu.pw.fizyka.pojava.LNM.Entity;

//by Cyprian Siwy
public class MovementManager {
    
    public Vector2D moveToNextPos(Vector2D startPos, Vector2D endPos, float speed, float timeStep) {
        Vector2D direction = Vector2D.remove(endPos, startPos);
        direction.normalize();

        direction = Vector2D.multiply(direction, speed * timeStep);
        return Vector2D.add(startPos, direction);
    }

    public Vector2D moveToDirection(Vector2D startPos, Vector2D direction, float speed, float timeStep) {
        direction.normalize();

        direction = Vector2D.multiply(direction, speed * timeStep);
        return Vector2D.add(startPos, direction);
    }
}