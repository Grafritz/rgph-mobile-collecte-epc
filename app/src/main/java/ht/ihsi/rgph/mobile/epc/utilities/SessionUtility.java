package ht.ihsi.rgph.mobile.epc.utilities;

import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;

/**
 * Created by JeanFritz on 4/12/2016.
 */
public class SessionUtility{

    //region ATTRIBUT
    private static QuestionnaireFormulaireUtility currentQuestionnaireFormulaireUtility;
    private static BatimentModel batimentModel;
    private static LogementModel logementModel;
    private static RowDataListModel rowDataListModel;
    //endregion

    //region GET / SET QuestionnaireFormulaireUtility
    public static QuestionnaireFormulaireUtility getCurrentQuestionnaireFormulaireUtility() {
        return currentQuestionnaireFormulaireUtility;
    }

    public static void setCurrentQuestionnaireFormulaireUtility(QuestionnaireFormulaireUtility currentQuestionnaireFormulaireUtility) {
        SessionUtility.currentQuestionnaireFormulaireUtility = currentQuestionnaireFormulaireUtility;
    }
    //endregion

    //region GET / SET BatimentModel
    public static BatimentModel getBatimentModel() {
        return batimentModel;
    }

    public static void setBatimentModel(BatimentModel batimentModel) {
        SessionUtility.batimentModel = batimentModel;
    }
    //endregion

    //region GET / SET LogementModel
    public static LogementModel getLogementModel() {
        return logementModel;
    }

    public static void setLogementModel(LogementModel logementModel) {
        SessionUtility.logementModel = logementModel;
    }
    //endregion

    //region GET / SET RowDataListModel

    public static RowDataListModel getRowDataListModel() {
        return rowDataListModel;
    }

    public static void setRowDataListModel(RowDataListModel rowDataListModel) {
        SessionUtility.rowDataListModel = rowDataListModel;
    }

    //endregion
}
