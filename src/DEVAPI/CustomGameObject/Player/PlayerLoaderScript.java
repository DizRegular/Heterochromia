package DEVAPI.CustomGameObject.Player;

import DEVAPI.CustomGameObject.Player.PlayerObject;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.Camera;
import RenderObject.ScriptSheet;
import RenderObject.Creatable.Vector2D;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

public class PlayerLoaderScript extends ScriptSheet {
    private boolean created = false;
    private double timer = 0;
    
    private PlayerObject player;
    private boolean jumping = false;
    private boolean shifting = false;
    private Vector2D playerSize = new Vector2D(70, 100);
    private Vector2D player2Size = new Vector2D(70, 145);
    private double ShiftableTime = 120;
    private double currShiftableTime = 0;
    private double switchCharacterTime = 120;
    private double currSwitchCharTime = 0;
    private double attackableTime = 30;
    private double currAttackableTime = 0;
    private boolean attacking = false;
    private boolean continuousHeal = false;
    private double continuousHealTimer = 50;
    private double currContinuousHealTimer = 0;
    private boolean burstDamage = false;
    private double burstDamageTimer = 600;
    private double currBurstDamageTimer = 0;
    
    private Camera playerCamera;
    
    private final String playerSwordIdlePathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Sword Idle/Player Sword Idle 48x48.png";
    private final String playerSwordRunPathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Sword Run/Player Sword Run 48x48.png";
    private final String playerSwordStabPathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Sword Stab/Player Sword Stab 96x48.png";
    private final String playerCrouchWalkPathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Crouch-Walk/player crouch-walk 48x48.png";
    private final String playerUseSkill1PathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Land/player land 48x48.png";
    private final String playerShiftPathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Roll/Player Roll 48x48.png";
    private final String playerJumpPathFile = "res/2D-Pixel-Art-Character-Template - Copy/2D-Pixel-Art-Character-Template/Jump/player new jump 48x48.png";
    
    private Animator playerSwordIdleAnim;
    private Animator playerSwordRunAnim;
    private Animator playerSwordStabAnim;
    private Animator playerCrouchWalkAnim;
    private Animator playerJumpAnim;
    private Animator playerShiftAnim;
    private Animator playerUseSkill1Anim;
    
    //-----------------------------------------------------------------
    
    private final String playerSword2IdlePathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/Idle.png";
    private final String playerSword2RunPathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/SlowRun_Loop.png";
    private final String playerSword2StabPathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/Attack2.png";
    private final String playerCrouch2WalkPathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/Crouch_Walk.png";
    private final String playerUseSkill2PathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/EnergyBuff.png";
    private final String playerShift2PathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/Dash_Loop.png";
    private final String playerJump2PathFile = "res/MainCharacter(FullPack)/MainCharacter(FullPack)/MainCharacter(FullPack-v1)/Air_Jump.png";
    
    private Animator playerSwordIdle2Anim;
    private Animator playerSwordRun2Anim;
    private Animator playerSwordStab2Anim;
    private Animator playerCrouchWalk2Anim;
    private Animator playerJump2Anim;
    private Animator playerDash2Anim;
    private Animator playerUseSkill2Anim;
    
    public enum animPriority {
        idle(0),
        walkRun(1),
        crouch(2),
        jump(3),
        attack(4),
        dashParry(5),
        skill(6),
        hurtStun(7),
        death(8);

        private final int priority;
        animPriority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return this.priority;
        }
    }
    
    public PlayerLoaderScript(String name) {
        super(name);
    }
    
    @Override
    public void onCreate() {
        player = GameUniverse.createInstance(new PlayerObject("ThePlayer"));
        player.setPosition(new Vector2D(100, 100));
        player.setSize(playerSize);
        
        playerSwordIdleAnim = GameUniverse.createInstance(new Animator("playerSwordIdleAnim"));
        playerSwordIdleAnim.createAnimationSheet(new Vector2D(48, 48), playerSwordIdlePathFile);
        player.addAnimator("playerSwordIdleAnim", playerSwordIdleAnim);
        playerSwordIdleAnim.setSpeed(10);
        
        playerSwordRunAnim = GameUniverse.createInstance(new Animator("playerSwordRunAnim"));
        playerSwordRunAnim.createAnimationSheet(new Vector2D(48, 48), playerSwordRunPathFile);
        player.addAnimator("playerSwordRunAnim", playerSwordRunAnim);
        playerSwordRunAnim.setSpeed(5);
        
        playerSwordStabAnim = GameUniverse.createInstance(new Animator("playerSwordStabAnim"));
        playerSwordStabAnim.createAnimationSheet(new Vector2D(96, 48), playerSwordStabPathFile);
        player.addAnimator("playerSwordStabAnim", playerSwordStabAnim);
        playerSwordStabAnim.setSpeed(5);
        playerSwordStabAnim.setUnloopable();
        
        playerCrouchWalkAnim = GameUniverse.createInstance(new Animator("playerCrouchWalkAnim"));
        playerCrouchWalkAnim.createAnimationSheet(new Vector2D(48, 48), playerCrouchWalkPathFile);
        player.addAnimator("playerCrouchWalkAnim", playerCrouchWalkAnim);
        playerCrouchWalkAnim.setSpeed(7);
        
        playerUseSkill1Anim = GameUniverse.createInstance(new Animator("playerUseSkill1Anim"));
        playerUseSkill1Anim.createAnimationSheet(new Vector2D(48, 48), playerUseSkill1PathFile);
        player.addAnimator("playerUseSkill1Anim", playerUseSkill1Anim);
        playerUseSkill1Anim.setSpeed(5);
        playerUseSkill1Anim.setUnloopable();
        
        playerShiftAnim = GameUniverse.createInstance(new Animator("playerShiftAnim"));
        playerShiftAnim.createAnimationSheet(new Vector2D(48, 48), playerShiftPathFile);
        player.addAnimator("playerShiftAnim", playerShiftAnim);
        playerShiftAnim.setUnloopable();
        playerShiftAnim.setSpeed(5);
        
        playerJumpAnim = GameUniverse.createInstance(new Animator("playerJumpAnim"));
        playerJumpAnim.createAnimationSheet(new Vector2D(48, 48), playerJumpPathFile);
        player.addAnimator("playerJumpAnim", playerJumpAnim);
        playerJumpAnim.setUnloopable();
        playerJumpAnim.setSpeed(9);
        
        //---------------------------------------------------------------------------------
        
        playerSwordIdle2Anim = GameUniverse.createInstance(new Animator("playerSwordIdle2Anim"));
        playerSwordIdle2Anim.createAnimationSheet(new Vector2D(192, 128), playerSword2IdlePathFile);
        player.addAnimator("playerSwordIdle2Anim", playerSwordIdle2Anim);
        playerSwordIdle2Anim.setSpeed(10);
        
        playerSwordRun2Anim = GameUniverse.createInstance(new Animator("playerSwordRun2Anim"));
        playerSwordRun2Anim.createAnimationSheet(new Vector2D(192, 128), playerSword2RunPathFile);
        player.addAnimator("playerSwordRun2Anim", playerSwordRun2Anim);
        playerSwordRun2Anim.setSpeed(7);
        
        playerSwordStab2Anim = GameUniverse.createInstance(new Animator("playerSwordStab2Anim"));
        playerSwordStab2Anim.createAnimationSheet(new Vector2D(192, 128), playerSword2StabPathFile);
        player.addAnimator("playerSwordStab2Anim", playerSwordStab2Anim);
        playerSwordStab2Anim.setSpeed(10);
        playerSwordStab2Anim.setUnloopable();
        
        playerCrouchWalk2Anim = GameUniverse.createInstance(new Animator("playerCrouchWalk2Anim"));
        playerCrouchWalk2Anim.createAnimationSheet(new Vector2D(192, 128), playerCrouch2WalkPathFile);
        player.addAnimator("playerCrouchWalk2Anim", playerCrouchWalk2Anim);
        playerCrouchWalk2Anim.setSpeed(7);
        
        playerUseSkill2Anim = GameUniverse.createInstance(new Animator("playerUseSkill2Anim"));
        playerUseSkill2Anim.createAnimationSheet(new Vector2D(192, 128), playerUseSkill2PathFile);
        player.addAnimator("playerUseSkill2Anim", playerUseSkill2Anim);
        playerUseSkill2Anim.setSpeed(10);
        playerUseSkill2Anim.setUnloopable();
        
        playerDash2Anim = GameUniverse.createInstance(new Animator("playerDash2Anim"));
        playerDash2Anim.createAnimationSheet(new Vector2D(192, 128), playerShift2PathFile);
        player.addAnimator("playerDash2Anim", playerDash2Anim);
        playerDash2Anim.setUnloopable();
        playerDash2Anim.setSpeed(5);
        
        playerJump2Anim = GameUniverse.createInstance(new Animator("playerJump2Anim"));
        playerJump2Anim.createAnimationSheet(new Vector2D(192, 128), playerJump2PathFile);
        player.addAnimator("playerJump2Anim", playerJump2Anim);
        playerJump2Anim.setUnloopable();
        playerJump2Anim.setSpeed(9);
        
        player.setCurrentAnimator("playerSwordIdleAnim", animPriority.idle.getPriority());
//        try {
//            player.addConstraint(GameUniverse.getObjectByName("PlayerCamera", Camera.class));
//        } catch (InvalidGameObjectPropertyException e) {
//            e.printStackTrace();
//        }
        
        
       playerCamera = GameUniverse.getObjectByName("PlayerCamera", Camera.class);
    }
    
    public void setTexutreToNormal() {
        CharacterSet charSet = player.getCurrentCharacterSet();
        if (charSet instanceof Character1) {
            player.setSize(playerSize);
            if (player.getDirectionX() == -1) {
                player.setTextureSize(new  Vector2D(-150, 150));
            } else {
                player.setTextureSize(new  Vector2D(150, 150));
            }
            
        } else if (charSet instanceof Character2) {
            player.setSize(player2Size);
            if (player.getDirectionX() == -1) {
                player.setTextureSize(new  Vector2D(-500, 300));
            } else {
                player.setTextureSize(new  Vector2D(500, 300));
            }
        } else if (charSet instanceof Character3) {
            
        }
        
    }
    
    @Override
    public void process(double deltaTime) {
        if (player.isDeath()) {
            player.setVisibility(true);
        }
        
        if (created == false) {
            created = true;
             player.setTextureSize(new Vector2D(150, 150));
        }
        boolean walkRight = InputManager.isKeyDown(KeyEvent.VK_D);
        boolean walkLeft = InputManager.isKeyDown(KeyEvent.VK_A);
        boolean attack = InputManager.isKeyDown(KeyEvent.VK_Q);
        boolean faceUp = InputManager.isKeyDown(KeyEvent.VK_W);
        boolean faceDown = InputManager.isKeyDown(KeyEvent.VK_S);
        boolean jump = InputManager.isKeyDown(KeyEvent.VK_SPACE);
        boolean useSkill = InputManager.isKeyDown(KeyEvent.VK_K);
        boolean shift = InputManager.isKeyDown(KeyEvent.VK_SHIFT);
        boolean num1 = InputManager.isKeyDown(KeyEvent.VK_1);
        boolean num2 = InputManager.isKeyDown(KeyEvent.VK_2);
        boolean num3 = InputManager.isKeyDown(KeyEvent.VK_3);
        
        boolean zoomIn = InputManager.isKeyDown(KeyEvent.VK_EQUALS);
        boolean zoomOut = InputManager.isKeyDown(KeyEvent.VK_MINUS);
        if (zoomIn) {
            playerCamera.setZoomFactor(playerCamera.getZoomFactor()-0.01);
        }
        if (zoomOut) {
            playerCamera.setZoomFactor(playerCamera.getZoomFactor()+0.01);
        }
        
        if (player.getTouchedFloor()) {jumping = false;}
        if (attacking == false ||(playerSwordStabAnim.hasFinished() || playerSwordStab2Anim.hasFinished())) {
            this.setTexutreToNormal();
            if ((!(walkLeft || walkRight) && (player.getTouchedFloor()) || player.getCurrPriority() == 0)) {
                player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.idle.getPriority()), animPriority.idle.getPriority());
            }
            if (walkLeft) {
                player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.walkRun.getPriority()), animPriority.walkRun.getPriority());
                player.flipXTexture(true);
                player.movePostion(new Vector2D((player.getSpeed()*-1)*deltaTime, 0));
            }
            if (walkRight) {
                player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.walkRun.getPriority()), animPriority.walkRun.getPriority());
                player.flipXTexture(false);
                player.movePostion(new Vector2D(player.getSpeed()*deltaTime, 0));
            }
            
            if (jump) {
                if(!jumping&& player.getTouchedFloor()){//add chack is tuch ground after addhitbox
                    jumping = true;
                    player.addVelocity(new Vector2D(0, -7));
                        player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.jump.getPriority()), animPriority.jump.getPriority());
                }
            }

        }
        
        if (attack && attackableTime < currAttackableTime && !attacking) {
            attacking = true;
            player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.attack.getPriority()), animPriority.attack.getPriority());
            player.getCurrentCharacterSet().normalAttack(player);
            if (player.getDirectionX() == -1) {
                player.setTextureSize(new Vector2D(-350, 150));
            } else {
                player.setTextureSize(new Vector2D(350, 150));
            }
            currAttackableTime = 0;
        } 
        
        
        if (!attack && faceDown && !jumping) {
            player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.crouch.getPriority()), animPriority.crouch.getPriority());
        }
        if (useSkill && !(walkLeft || walkRight) && player.getTouchedFloor() ) {
            CharacterSet pcharacter = player.getCurrentCharacterSet();
            player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.skill.getPriority()), animPriority.skill.getPriority());
            if (pcharacter instanceof Character1 && player.getHealthPoint() < player.getMaxHealthPoint()) {
                pcharacter.useSkill(player);
                continuousHeal = true;
            } if (pcharacter instanceof Character2) {
                pcharacter.useSkill(player);
                burstDamage = true;
            }
        }

        if (shift && jumping && !shifting) {
            if (currShiftableTime >= ShiftableTime) {
                if (!(player.getCurrentCharacterSet() instanceof Character1)) {
                    player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.dashParry.getPriority()), animPriority.dashParry.getPriority());
                    if (player.getDirectionX() == -1) {
                        player.movePostion(new Vector2D(-150*deltaTime, 0));
                    } else {
                        player.movePostion(new Vector2D(150*deltaTime, 0));
                    }
                } else {
                    player.setCurrentAnimator("playerShiftAnim", animPriority.dashParry.getPriority());
                    player.setInvicibility(true);
                    player.setParry(true);
                }
                shifting = true;
                currShiftableTime = 0;
            } 
            
        }
        if ( shifting &&(playerShiftAnim.hasFinished() || playerDash2Anim.hasFinished())) {
            shifting = false;
            if (player.isParrying()) {
                player.setParry(false);
                player.setInvicibility(false);
            }
            playerShiftAnim.setFinished(true);
        }
        if (currSwitchCharTime > switchCharacterTime && !(walkLeft || walkRight) && player.getTouchedFloor()) {
            if (num1 || num2 || num3) {
                currSwitchCharTime = 0;
                this.setTexutreToNormal();
                player.setCurrPriority(0);
                player.setCurrentAnimator(player.getCurrentCharacterSet().getSpecificCharacterAnimationName(animPriority.idle.getPriority()), animPriority.idle.getPriority());
            }
            if (num1 ) {
                player.switchCharacter(1);
            } else if (num2) {
                player.switchCharacter(2);
                player.movePostion(new Vector2D(0, -40));
            } else if (num3) {
                player.switchCharacter(3);
            }
                            
        }
        if (attacking == true && (playerSwordStabAnim.hasFinished()|| playerSwordStab2Anim.hasFinished()) && !attack) {
            attacking = false;
        }
        if (continuousHeal) {
            currContinuousHealTimer += deltaTime;
            player.takeDamage(-0.5, player);
            if (currContinuousHealTimer > continuousHealTimer) {
                currContinuousHealTimer = 0;
                continuousHeal = false;
            }
        }
        player.printStat();
        if (burstDamage) {
            currBurstDamageTimer += deltaTime;
            player.takeDamage(0.1, player);
            System.out.println(currBurstDamageTimer + "/" + burstDamageTimer);
            if (currBurstDamageTimer > burstDamageTimer) {
                player.setBaseDamage(10);
                currBurstDamageTimer = 0;
                burstDamage = false;
            }
        }
        
        currAttackableTime += deltaTime;
        currSwitchCharTime += deltaTime;
        currShiftableTime += deltaTime;
    }
    
}
