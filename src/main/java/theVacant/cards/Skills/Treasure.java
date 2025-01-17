package theVacant.cards.Skills;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVacant.VacantMod;
import theVacant.actions.MineGemAction;
import theVacant.cards.AbstractDynamicCard;
import theVacant.characters.TheVacant;
import theVacant.orbs.EmeraldOrb;
import theVacant.orbs.OpalOrb;
import theVacant.orbs.RubyOrb;

import java.util.ArrayList;

import static theVacant.VacantMod.makeCardPath;

public class Treasure extends AbstractDynamicCard
{

    public static final String ID = VacantMod.makeID(Treasure.class.getSimpleName());
    public static final String IMG = makeCardPath("Treasure.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVacant.Enums.COLOR_GOLD;

    private static final int COST = 0;

    private static ArrayList<TooltipInfo> ExtraTooltip;

    public Treasure()
    {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        addToBot(new MineGemAction(new EmeraldOrb(magicNumber)));
        addToBot(new MineGemAction(new OpalOrb(1)));
//        int vigorAmount = player.discardPile.size();
//        if(vigorAmount > 0)
//            addToBot(new ApplyPowerAction(player, player, new VigorPower(player, vigorAmount), vigorAmount));
//        addToBot(new SwitchFormAction(BoundSoul.FURY_FORM));
//        if(upgraded)
//            addToBot(new SyphonAction(magicNumber));
    }

//    @Override
//    public List<TooltipInfo> getCustomTooltips()
//    {
//        if(ExtraTooltip == null)
//        {
//            ExtraTooltip = new ArrayList<>();
//            ExtraTooltip.add(new TooltipInfo(BaseMod.getKeywordProper(KeywordManager.FURY_FORM_ID), BaseMod.getKeywordDescription(KeywordManager.FURY_FORM_ID)));
//        }
//        return ExtraTooltip;
//    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            upgradeName();
            upgradeMagicNumber(1);
            upgradedMagicNumber = true;
            initializeDescription();
        }
    }
}