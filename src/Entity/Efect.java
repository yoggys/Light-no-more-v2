package Entity;

public class Efect
{
    int timeInTurn = 0;
    int damagePerTurn = 0;

    public Efect(int damage, int time)
    {
        timeInTurn=time;
        damagePerTurn = damage;
    }

    public Efect(Efect efect)
    {
        this.damagePerTurn = efect.damagePerTurn;
        this.timeInTurn = efect.timeInTurn;
    }

    public int getTotal(){
        return this.damagePerTurn*this.timeInTurn;
    }

    public void use(Someone target)
    {
        if(timeInTurn>0)
        {
            if(damagePerTurn>0)
            {
                target.takeDamage(damagePerTurn);
            }
            else
            {
                target.takeHeal(-damagePerTurn);
            }
            timeInTurn--;
        }
    }

    public int getTime() { return timeInTurn; }

    public void upgradeEfect(double rand){
        this.damagePerTurn += damagePerTurn*rand;
    }
}