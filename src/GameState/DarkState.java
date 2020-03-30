package GameState;

import Entity.*;
import System.*;
import Player.*;
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

	Champion emptyChamp = new Champion(0,0,"");

	private int firstChampX = 200, firstChampY = 400, firstEnemyX = 600, firstEnemyY = 400;

	public DarkState(GameStateManager gsm) 
	{
		
		activeChamp = emptyChamp;
		poison = new Efect(2, 3);
		healOverTime = new Efect(-2 , 3);

		Skill skillPoison = new Skill("Poison", 4, 2, poison);
		Skill skillSlise = new Skill("Slise",20,5);
		Skill skillSmite = new Skill("Smite",10,20);
		Skill skillHeal = new Skill("Heal",-4, 2, healOverTime);
		Player.champions.add(new Champion(25, 20, "AleXXX"));
		Player.champions.add(new Champion(25, 20, "Sasha"));
		Player.champions.add(new Champion(100, 30, "Siwy"));
		Player.enemys.add(new Someone(50, 10, "wolf"));
		Player.champions.get(0).addSkill( new Skill(skillSlise ));
		Player.champions.get(0).addSkill( new Skill(skillSmite ));
		Player.champions.get(1).addSkill( new Skill(skillHeal  ));
		Player.champions.get(1).addSkill( new Skill(skillPoison ));
		Player.champions.get(2).addSkill( new Skill(skillSmite ));
		Player.champions.get(2).addSkill( new Skill(skillHeal  ));

		this.gsm = gsm;
		
		try {
			
			bg = new Background("Resources/Backgrounds/darkbg.png");

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

					
					Player.champions.get(i).drawSomeone(firstChampX + i *100, firstChampY, g);
					
				} 
				else if(i< Player.champions.size() + Player.enemys.size())
				{
					Player.enemys.get(i - Player.champions.size()).drawSomeone(firstEnemyX + i*100, firstEnemyY, g);
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

					activeChamp.skills.get(j).drawSkill(firstChampX + j * 75, firstChampY + 50, g);
					//g.drawString(activeChamp.skills.get(j).getName(), firstChampX + j*50, firstChampY + 50);
				}
				else
				{
					g.drawString("back",  586, 700);
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
				currentChoice = 0;
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
		

		if(k == KeyEvent.VK_UP)
		{
			
			if( currentChoice == Player.champions.size() + Player.enemys.size() +activeChamp.skills.size())
			{
				if(activeChamp.skills.size()>0)
					currentChoice = Player.champions.size() + Player.enemys.size();
				else
					currentChoice = 0;
			}
			else if (currentChoice >= Player.champions.size() + Player.enemys.size())
			{
				currentChoice = activeChampId;
			}
		} 

		if(k == KeyEvent.VK_RIGHT) 
		{
			if(currentChoice == Player.champions.size() + Player.enemys.size()-1  || 
			currentChoice >= Player.champions.size() + Player.enemys.size() + activeChamp.skills.size()-1)
			{}
			else
				currentChoice++;
		}


		if(k == KeyEvent.VK_DOWN)
		{
			if (currentChoice<Player.champions.size() + Player.enemys.size())
			{
				currentChoice = Player.champions.size() + Player.enemys.size();
			} 
			else if( currentChoice >= Player.champions.size() + Player.enemys.size())
			{
				currentChoice = Player.champions.size()+Player.enemys.size()+activeChamp.skills.size();
			}
		}
		
		if(k == KeyEvent.VK_LEFT) 
		{
			if(currentChoice == 0 || currentChoice == Player.champions.size() + Player.enemys.size() 
			|| currentChoice == Player.champions.size() + Player.enemys.size() + activeChamp.skills.size())
			{}
			else 
				currentChoice--;
		}
		if(k == KeyEvent.VK_ESCAPE) 
		{
			EscState.back = gsm.getState();
			gsm.setState(GameStateManager.ESCSTATE);
		}
	}


	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_ENTER)
		tmp=true;
	}

}