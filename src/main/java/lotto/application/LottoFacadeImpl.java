package lotto.application;

import lotto.domain.*;

import java.util.List;



public class LottoFacadeImpl implements LottoFacade{

    private final LottoProcessor lottoProcessor = new LottoProcessorImpl();
    private final MoneyProcessor moneyProcessor = new MoneyProcessorImpl();
    private final ValidatorProcessor validatorProcessor = new ValidatorProcessorImpl();


    @Override
    public List<Lotto> buyLotto(Integer money) {
        validatorProcessor.validateMoney(money, PriceEnum.LOTTO_PRICE);
        Integer count = moneyProcessor.calculateLottoCount(money);
        return lottoProcessor.createLottoByCount(count);
    }

    @Override
    public Lotto registerWinLotto(String input) {
        List<Integer> validatedLottoNumber = validatorProcessor.validateLottoNumberInput(input);
        validatorProcessor.validateLottoSize(validatedLottoNumber.size(), LottoEnum.LOTTO);
        return lottoProcessor.createLotto(validatedLottoNumber);
    }

    @Override
    public String getMargin(Integer before, Integer after) {
        return moneyProcessor.calculateMargin(before, after);
    }

}
