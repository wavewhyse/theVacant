package theVacant.cards.Attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVacant.VacantMod;
import theVacant.cards.AbstractDynamicCard;
import theVacant.characters.TheVacant;
import theVacant.powers.ReapPower;
import theVacant.vfx.ReapVFX;

import static theVacant.VacantMod.makeCardPath;

public class ReaperStrike extends AbstractDynamicCard
{

    public static final String ID = VacantMod.makeID(ReaperStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("ReaperStrike.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVacant.Enums.COLOR_GOLD;

    private static final int COST = 2;
    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 4;

    public ReaperStrike()
    {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
        isMultiDamage = true;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        addToBot(new VFXAction(new ReapVFX(), ReapVFX.DURATION*3/4F));
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new DamageAllEnemiesAction(player, DamageInfo.createDamageMatrix(damage, true), damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            addToBot(new ApplyPowerAction(mo, player, new ReapPower(mo, mo, magicNumber),magicNumber));
    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(1);
            upgradedMagicNumber = true;
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradedDamage = true;
            initializeDescription();
        }
    }
}