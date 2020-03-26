package GameState;

import TileMap.Background;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class DarkState extends GameState {
	
    private Background bg;
	private Champion activeChamp = null;
	private int activeChampId=999;
	private Efect poison;
	private Efect healOverTime;

	private Skill selectedSkill = null;
	private int selectedSkillId=999;

	private Someone target = null;
	private int targetId=999;
	
	private Someone enemy = null;

	private int currentChoice = 0;

	private Font font;
	
	private boolean tmp=true;

	Champion emptyChamp = new Champion(0,0,0,"");

	public DarkState(GameStateManager gsm) 
	{
		
		activeChamp = emptyChamp;
		poison = new Efect(2, 3);
		healOverTime = new Efect(-2 , 3);

		Skill skillPoison = new Skill("Poison", 4, 2, poison);
		Skill skillSlise = new Skill("Slise",20,5);
		Skill skillSmite = new Skill("Smite",10,20);
		Skill skillHeal = new Skill("Heal",-4, 2, healOverTime);
		Player.champions.add(new Champion(25, 10, 20, "AleXXX"));
		Player.champions.add(new Champion(25, 10, 20, "Sasha"));
		Player.champions.add(new Champion(100, 10, 30, "Siwy"));
		Player.enemys.add(new Someone(50, 0, 10, "wolf"));
		Player.champions.get(0).addSkill( skillSlise );
		Player.champions.get(0).addSkill( skillSmite );
		Player.champions.get(1).addSkill( skillHeal );
		Player.champions.get(1).addSkill( skillPoison );
		Player.champions.get(2).addSkill( skillSmite );
		Player.champions.get(2).addSkill( skillHeal );

		this.gsm = gsm;
		
		try {
			
			bg = new Background("/Backgrounds/darkbg.png");

			font = new Font("Arial", Font.PLAIN, 12);
			
		}

		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
    public void draw(Graphics2D g) 
    {

		bg.draw(g);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		if(Player.champions.size() == 0)
		{
			g.setColor(Color.WHITE);
			g.drawString("back", 972, 820);
        }
			for(int i = 0; i < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()+1; i++) 
			{
				if(i == currentChoice)
				{
					g.setColor(Color.WHITE);
				}
				else if(i == activeChampId || i == selectedSkillId)
				{
					g.setColor(Color.green);
				}
				else if(i == targetId)
				{
					g.setColor(Color.ORANGE);
				}
				else 
				{
					g.setColor(Color.RED);
				}
				
				if(i<Player.champions.size())
				{
					if( ! Player.champions.get(i).isActive())
					{
						if(g.getColor()==Color.WHITE)
						g.setColor(Color.LIGHT_GRAY);
						else
						g.setColor(Color.GRAY);
					}

					g.drawString(Player.champions.get(i).getName(), 100 + i*50, 100);
					g.drawString(""+Player.champions.get(i).getHp(), 100 + i*50, 120);
					g.drawString(""+Player.champions.get(i).getStamina(), 120 + i*50, 120);
				} 
				else if(i< Player.champions.size() + Player.enemys.size())
				{
					g.drawString(Player.enemys.get(i - Player.champions.size()).getName(), 200 + i*50, 100);
					g.drawString(""+Player.enemys.get(i - Player.champions.size()).getHp(), 200 + i*50, 120);
					g.drawString(""+Player.enemys.get(i - Player.champions.size()).getStamina(), 220 + i*50, 120);
				}
				else if(activeChamp!=null && i < Player.champions.size() + Player.enemys.size() + activeChamp.skills.size())
				{
					int j = i - Player.champions.size() - Player.enemys.size();
					
					if(activeChamp.skills.get(j).getStaminaUse() > activeChamp.getStamina())
					{
						if(g.getColor()==Color.WHITE)
						g.setColor(Color.LIGHT_GRAY);
						else
						g.setColor(Color.GRAY);
					}

					g.drawString(activeChamp.skills.get(j).getName(), 100+ j*50, 150);
				}
				else
				{
					g.drawString("back",  586, 410);
				}
				
				if(activeChamp!=null && target != null && selectedSkill != null)
				{
					calculateMove();
					currentChoice=0;
				}

				int x=0;
				for (Champion champion : Player.champions) 
				{
					if(champion.isActive())
					x++;
				}

				if( x == 0 )
				{
					endTurn();
				}
				
			}   
	}
	
	void calculateMove()
	{
		if(selectedSkill.getEfect()!=null)
		target.addEfect(new Efect( selectedSkill.getEfect() ) );

		activeChamp.useStamina(selectedSkill.getStaminaUse());

		target.takeDamage(selectedSkill.getDamage());
		activeChamp.setActive(false);
		
		resetChoises();
	}

	void resetChoises()
	{
		activeChamp=emptyChamp;
		activeChampId=999;
		target=null;
		targetId=999;
		selectedSkill=null;
		selectedSkillId=999;
	}
	
	void endTurn()
	{
		for (Champion champion : Player.champions) 
		{
			for (int i = 0 ; i <champion.efects.size();i++) 
			{
				champion.efects.get(i).use(champion);	
		
				if(champion.efects.get(i).getTime()==0)
				{
					champion.efects.remove(i);
				}
			}

			champion.setActive(true);
		}
	
		for (int i = 0; i<Player.enemys.size();i++) 
		{
			enemy = Player.enemys.get(i);
			for(int j =0 ; j<enemy.efects.size();j++)
			//for (Efect efect : enemy.efects) 
			{
				enemy.efects.get(j).use(enemy);
		
				if(enemy.efects.get(j).getTime()==0)
				{
					enemy.efects.remove(j);
				}
			}		
		}
	}
	
	
	private void select() 
	{
		
		if(selectedSkill != null && currentChoice < Player.champions.size() + Player.enemys.size())
		{
			if(currentChoice<Player.champions.size())
			{
				target = Player.champions.get(currentChoice);
				targetId = currentChoice;
			}
			else
			{
				target=Player.enemys.get(currentChoice - Player.champions.size());
				targetId = currentChoice;
			}
		}

		if(currentChoice<Player.champions.size() && selectedSkill == null)
		{
			if(activeChamp.isActive())
			{
				if(Player.champions.get(currentChoice).isActive())
				{
					activeChamp = Player.champions.get(currentChoice);
					activeChampId = currentChoice;
					selectedSkill = null;
					selectedSkillId = 999;
				}
			}
		}
		else if(currentChoice< Player.champions.size() + Player.enemys.size() + activeChamp.skills.size() && currentChoice >= Player.champions.size() + Player.enemys.size())
		{
			selectedSkill = activeChamp.skills.get(currentChoice - Player.champions.size() - Player.enemys.size() );
			if(selectedSkill.getStaminaUse() < activeChamp.getStamina())
			{
				selectedSkillId = currentChoice;
			}
			else
			{
				selectedSkill = null;
				selectedSkillId = 999;
			}

		}

		if(currentChoice == Player.champions.size() + Player.enemys.size() + activeChamp.skills.size())
		{
			gsm.setState(GameStateManager.TOWNSTATE);
		}	
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER)
		{
			if(tmp)
			{
				select();
				tmp=false;
			}
		}

		if(k == 32) //Space
		{
		}
		

		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_RIGHT) {
			currentChoice++;
			if(currentChoice == Player.champions.size()+Player.enemys.size()+activeChamp.skills.size()+1) {
				currentChoice = 0;
			}
		}
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_LEFT) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = Player.champions.size()+Player.enemys.size()+activeChamp.skills.size();
			}
		}
	}
	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_ENTER)
		tmp=true;
	}

}