package Entity;

//by Cyprian Siwy
public class Effect
{
    private int timeInTurn = 0;
    private int damagePerTurn = 0;

    public Effect(int damage, int time)
    {
        timeInTurn=time;
        damagePerTurn = damage;
    }

    public Effect(Effect effect)
    {
        this.damagePerTurn = effect.damagePerTurn;
        this.timeInTurn = effect.timeInTurn;
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
    public int getDamage() { return damagePerTurn; }

    //by Mateusz Karbownik
    public int getTotal(){
        return this.damagePerTurn*this.timeInTurn;
    }

    public void upgradeEfect(double rand){
        this.damagePerTurn += damagePerTurn*rand;
    }
}