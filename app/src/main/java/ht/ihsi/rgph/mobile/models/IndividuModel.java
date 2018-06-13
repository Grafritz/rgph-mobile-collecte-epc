package ht.ihsi.rgph.mobile.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.utilities.Tools;

/**
 * Created by ajordany on 3/21/2016.
 */
public class IndividuModel extends BaseModel{

//region ATTRIBUT
private Long individuId;
    private Long menageId;
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short q1NoOrdre;
    private String qp2APrenom;
    private String qp2BNom;
    private Short qp3LienDeParente;
    private Short qp3HabiteDansMenage;
    private Short qp4Sexe;
    private Short qp5DateNaissanceJour;
    private Short qp5DateNaissanceMois;
    private Integer qp5DateNaissanceAnnee;
    private Short qp5bAge;
    private Short qp6religion;
    private String qp6AutreReligion;
    private Short qp7Nationalite;
    private String qp7PaysNationalite;
    private Short qp8MereEncoreVivante;
    private Short qp9EstPlusAge;
    private Short qp10LieuNaissance;
    private String qp10CommuneNaissance;
    private String qp10VqseNaissance;
    private String qp10PaysNaissance;
    private Short qp11PeriodeResidence;
    private Short qp12DomicileAvantRecensement;
    private String qp12CommuneDomicileAvantRecensement;
    private String qp12VqseDomicileAvantRecensement;
    private String qp12PaysDomicileAvantRecensement;
    private Short qe1EstAlphabetise;
    private Short qe2FreqentationScolaireOuUniv;
    private Short qe3typeEcoleOuUniv;
    private Short qe4aNiveauEtude;
    private String qe4bDerniereClasseOUAneEtude;
    private Short qe5DiplomeUniversitaire;
    private String qe6DomaineEtudeUniversitaire;
    private Short qaf1HandicapVoir;
    private Short qaf2HandicapEntendre;
    private Short qaf3HandicapMarcher;
    private Short qaf4HandicapSouvenir;
    private Short qaf5HandicapPourSeSoigner;
    private Short qaf6HandicapCommuniquer;
    private Short qt1PossessionTelCellulaire;
    private Short qt2UtilisationInternet;
    private Short qem1DejaVivreAutrePays;
    private String qem1AutrePays;
    private Short qem2MoisRetour;
    private Integer qem2AnneeRetour;
    private Short qsm1StatutMatrimonial;
    private Short qa1ActEconomiqueDerniereSemaine;
    private Short qa2ActAvoirDemele1;
    private Short qa2ActDomestique2;
    private Short qa2ActCultivateur3;
    private Short qa2ActAiderParent4;
    private Short qa2ActAutre5;
    private Short qa3StatutEmploie;
    private Short qa4SecteurInstitutionnel;
    private String qa5TypeBienProduitParEntreprise;
    private String qa5PreciserTypeBienProduitParEntreprise;
    private Short qa6LieuActDerniereSemaine;
    private Short qa7FoncTravail;
    private Short qa8EntreprendreDemarcheTravail;
    private Short qa9VouloirTravailler;
    private Short qa10DisponibilitePourTravail;
    private Short qa11RecevoirTransfertArgent;
    private Integer qf1aNbreEnfantNeVivantM;
    private Integer qf1bNbreEnfantNeVivantF;
    private Integer qf2aNbreEnfantVivantM;
    private Integer qf2bNbreEnfantVivantF;
    private Short qf3DernierEnfantJour;
    private Short qf3DernierEnfantMois;
    private Integer qf3DernierEnfantAnnee;
    private Short qf4DENeVivantVit;
    private Short statut;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;
//endregion

//region VARIABLE SYSTEME
    private String qp5JourMoisAnneeDateNaissance;
    private String qe4ANiveauEtudeETClasse;
    private String qem2MoisAnneeRetour;
    private String qf1NbreEnfantNeVivantGarconEtFille;
    private String qf2NbrEnfantVivantGarconEtFille;
    private String qf3JourMoisAnneeDernierEnfant;

    public boolean IsAgeIndividuVerify=true;
//endregion

    //region MODEL OBJET
    private BatimentModel objBatiment;
    private LogementModel objLogement;
    private MenageModel objMenage;
    public static QueryRecordMngr queryRecordMngr;
    //endregion

    public IndividuModel() {
        this.individuId = Long.valueOf(0);
        BlankProperties();
    }

    public IndividuModel(long individuId, short q1NoOrdre, String qp2APrenom, String qp2BNom) {
        this.individuId = individuId;
        this.q1NoOrdre = q1NoOrdre;
        this.qp2APrenom = qp2APrenom;
        this.qp2BNom = qp2BNom;
    }

    private void BlankProperties() {
        this.menageId = Long.valueOf(0);
        this.logeId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.sdeId = "";
        this.q1NoOrdre = 0;
        this.qp2APrenom = "";
        this.qp2BNom = "";
        this.qp3LienDeParente = 0;
        this.qp3HabiteDansMenage = 0;
        this.qp4Sexe = 0;
        this.qp5DateNaissanceJour = 0;
        this.qp5DateNaissanceMois = 0;
        this.qp5DateNaissanceAnnee = 0;
        this.qp5bAge = 0;
        this.qp6religion = 0;
        this.qp6AutreReligion = "";
        this.qp7Nationalite = 0;
        this.qp7PaysNationalite = "";
        this.qp8MereEncoreVivante = 0;
        this.qp9EstPlusAge = 0;
        this.qp10LieuNaissance = 0;
        this.qp10CommuneNaissance = "";
        this.qp10VqseNaissance = "";
        this.qp10PaysNaissance = "";
        this.qp11PeriodeResidence = 0;
        this.qp12DomicileAvantRecensement = 0;
        this.qp12CommuneDomicileAvantRecensement = "";
        this.qp12VqseDomicileAvantRecensement = "";
        this.qp12PaysDomicileAvantRecensement = "";
        this.qe1EstAlphabetise = 0;
        this.qe2FreqentationScolaireOuUniv = 0;
        this.qe3typeEcoleOuUniv = 0;
        this.qe4aNiveauEtude = 0;
        this.qe4bDerniereClasseOUAneEtude = "";
        this.qe5DiplomeUniversitaire = 0;
        this.qe6DomaineEtudeUniversitaire = "";
        this.qaf1HandicapVoir = 0;
        this.qaf2HandicapEntendre = 0;
        this.qaf3HandicapMarcher = 0;
        this.qaf4HandicapSouvenir = 0;
        this.qaf5HandicapPourSeSoigner = 0;
        this.qaf6HandicapCommuniquer = 0;
        this.qt1PossessionTelCellulaire = 0;
        this.qt2UtilisationInternet = 0;
        this.qem1DejaVivreAutrePays = 0;
        this.qem1AutrePays = "";
        this.qem2MoisRetour = 0;
        this.qem2AnneeRetour = 0;
        this.qsm1StatutMatrimonial = 0;
        this.qa1ActEconomiqueDerniereSemaine = 0;
        this.qa2ActAvoirDemele1 = 0;
        this.qa2ActDomestique2 = 0;
        this.qa2ActCultivateur3 = 0;
        this.qa2ActAiderParent4 = 0;
        this.qa2ActAutre5 = 0;
        this.qa3StatutEmploie = 0;
        this.qa4SecteurInstitutionnel = 0;
        this.qa5TypeBienProduitParEntreprise = "";
        this.qa5PreciserTypeBienProduitParEntreprise = "";
        this.qa6LieuActDerniereSemaine = 0;
        this.qa7FoncTravail = 0;
        this.qa8EntreprendreDemarcheTravail = 0;
        this.qa9VouloirTravailler = 0;
        this.qa10DisponibilitePourTravail = 0;
        this.qa11RecevoirTransfertArgent = 0;
        this.qf1aNbreEnfantNeVivantM = 0;
        this.qf1bNbreEnfantNeVivantF = 0;
        this.qf2aNbreEnfantVivantM = 0;
        this.qf2bNbreEnfantVivantF = 0;
        this.qf3DernierEnfantJour = 0;
        this.qf3DernierEnfantMois = 0;
        qf3DernierEnfantAnnee = 0;
        this.qf4DENeVivantVit = 0;
        this.statut = 0;
        isFieldAllFilled = false;
        this.dateDebutCollecte = "";
        this.dateFinCollecte = "";
        dureeSaisie = 0;
        isContreEnqueteMade = false;
        this.codeAgentRecenceur = "";
    }

    // region |-| GETTER SETTER SYSTEME |-|
    public String getQp5JourMoisAnneeDateNaissance() { // "MM-dd-yyyy"
        return qp5DateNaissanceMois + "-" + qp5DateNaissanceJour + "-" + qp5DateNaissanceAnnee;
    }
    public void setQp5JourMoisAnneeDateNaissance(String value) {
        try {
            /* Short qpDateNaissanceJour / Short qpDateNaissanceMois / Integer qpDateNaissanceAnnee;*/
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            qp5DateNaissanceMois = Short.valueOf(mois);
            qp5DateNaissanceJour = Short.valueOf(jour);
            qp5DateNaissanceAnnee = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQe4ANiveauEtudeETClasse() {// "00-00"
        return qe4aNiveauEtude + "-" + qe4bDerniereClasseOUAneEtude;
    }
    public void setQe4ANiveauEtudeETClasse(String value) {
        try {
            String[] Items = value.split("-"); // 00-00
            qe4aNiveauEtude = Short.valueOf(Items[0]);
            qe4bDerniereClasseOUAneEtude = String.valueOf(Items[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQem2MoisAnneeRetour() { // "MM-dd-yyyy"
        return qem2MoisRetour + "-01-" + qem2AnneeRetour;
    }
    public void setQem2MoisAnneeRetour(String value) {
        try {
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            qem2MoisRetour = Short.valueOf(mois);
            qem2AnneeRetour = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQf1NbreEnfantNeVivantGarconEtFille() {// "00-00"
        return qf1aNbreEnfantNeVivantM + "-" + qf1bNbreEnfantNeVivantF;
    }
    public void setQf1NbreEnfantNeVivantGarconEtFille(String value) {
        try {
            String[] Items = value.split("-"); // 00-00
            String items  = Items[0];
            qf1aNbreEnfantNeVivantM = Integer.valueOf(items);
            items = Items[1];
            qf1bNbreEnfantNeVivantF = Integer.valueOf(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQf2NbrEnfantVivantGarconEtFille() {// "00-00"
        return qf2aNbreEnfantVivantM + "-" + qf2bNbreEnfantVivantF;
    }
    public void setQf2NbrEnfantVivantGarconEtFille(String value) {
        try {
            String[] Items = value.split("-"); // 00-00
            String items  = Items[0];
            qf2aNbreEnfantVivantM = Integer.valueOf(items);
            items = Items[1];
            qf2bNbreEnfantVivantF = Integer.valueOf(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQf3JourMoisAnneeDernierEnfant() {
        // "MM-dd-yyyy"
        return qf3DernierEnfantMois + "-" + qf3DernierEnfantJour + "-" + qf3DernierEnfantAnnee;
    }
    public void setQf3JourMoisAnneeDernierEnfant(String value) {
        /* private Short qf3DernierEnfantJour;     private Short qf3DernierEnfantMois;    private Integer qf3DernierEnfantAnnee;*/
        try {
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            qf3DernierEnfantJour = Short.valueOf(jour);
            qf3DernierEnfantMois = Short.valueOf(mois);
            qf3DernierEnfantAnnee = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//endregion///////////////////////////////////////////////

    //region IndividuModel getters and setters
    public Long getIndividuId() {
        return individuId;
    }

    public void setIndividuId(Long individuId) {
        this.individuId = individuId;
    }

    public Long getMenageId() {
        return menageId;
    }

    public void setMenageId(Long menageId) {
        this.menageId = menageId;
    }

    public Long getLogeId() {
        return logeId;
    }

    public void setLogeId(Long logeId) {
        this.logeId = logeId;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public Short getQ1NoOrdre() {
        return q1NoOrdre;
    }

    public void setQ1NoOrdre(Short q1NoOrdre) {
        this.q1NoOrdre = q1NoOrdre;
    }

    public String getQp2APrenom() {
        return qp2APrenom;
    }

    public void setQp2APrenom(String qp2APrenom) {
        this.qp2APrenom = qp2APrenom;
    }

    public String getQp2BNom() {
        return qp2BNom;//.toUpperCase();
    }

    public void setQp2BNom(String qp2BNom) {
        this.qp2BNom = qp2BNom;
    }

    public Short getQp3LienDeParente() {
        return qp3LienDeParente;
    }

    public void setQp3LienDeParente(Short qp3LienDeParente) {
        this.qp3LienDeParente = qp3LienDeParente;
    }

    public Short getQp3HabiteDansMenage() {
        return qp3HabiteDansMenage;
    }

    public void setQp3HabiteDansMenage(Short qp3HabiteDansMenage) {
        this.qp3HabiteDansMenage = qp3HabiteDansMenage;
    }

    public Short getQp4Sexe() {
        return qp4Sexe;
    }

    public void setQp4Sexe(Short qp4Sexe) {
        this.qp4Sexe = qp4Sexe;
    }

    public Short getQp5DateNaissanceJour() {
        return qp5DateNaissanceJour;
    }

    public void setQp5DateNaissanceJour(Short qp5DateNaissanceJour) {
        this.qp5DateNaissanceJour = qp5DateNaissanceJour;
    }

    public Short getQp5DateNaissanceMois() {
        return qp5DateNaissanceMois;
    }

    public void setQp5DateNaissanceMois(Short qp5DateNaissanceMois) {
        this.qp5DateNaissanceMois = qp5DateNaissanceMois;
    }

    public Integer getQp5DateNaissanceAnnee() {
        return qp5DateNaissanceAnnee;
    }

    public void setQp5DateNaissanceAnnee(Integer qp5DateNaissanceAnnee) {
        this.qp5DateNaissanceAnnee = qp5DateNaissanceAnnee;
    }

    public Short getQp5bAge() {
        return qp5bAge;
    }

    public void setQp5bAge(Short qp5bAge) {
        this.qp5bAge = qp5bAge;
    }

    public Short getQp6religion() {
        return qp6religion;
    }

    public void setQp6religion(Short qp6religion) {
        this.qp6religion = qp6religion;
    }

    public String getQp6AutreReligion() {
        return qp6AutreReligion;
    }

    public void setQp6AutreReligion(String qp6AutreReligion) {
        this.qp6AutreReligion = qp6AutreReligion;
    }

    public Short getQp7Nationalite() {
        return qp7Nationalite;
    }

    public void setQp7Nationalite(Short qp7Nationalite) {
        this.qp7Nationalite = qp7Nationalite;
    }

    public String getQp7PaysNationalite() {
        return qp7PaysNationalite;
    }

    public void setQp7PaysNationalite(String qp7PaysNationalite) {
        this.qp7PaysNationalite = qp7PaysNationalite;
    }

    public Short getQp8MereEncoreVivante() {
        return qp8MereEncoreVivante;
    }

    public void setQp8MereEncoreVivante(Short qp8MereEncoreVivante) {
        this.qp8MereEncoreVivante = qp8MereEncoreVivante;
    }

    public Short getQp9EstPlusAge() {
        return qp9EstPlusAge;
    }

    public void setQp9EstPlusAge(Short qp9EstPlusAge) {
        this.qp9EstPlusAge = qp9EstPlusAge;
    }

    public Short getQp10LieuNaissance() {
        return qp10LieuNaissance;
    }

    public void setQp10LieuNaissance(Short qp10LieuNaissance) {
        this.qp10LieuNaissance = qp10LieuNaissance;
    }

    public String getQp10CommuneNaissance() {
        return qp10CommuneNaissance;
    }

    public void setQp10CommuneNaissance(String qp10CommuneNaissance) {
        this.qp10CommuneNaissance = qp10CommuneNaissance;
    }

    public String getQp10VqseNaissance() {
        return qp10VqseNaissance;
    }

    public void setQp10VqseNaissance(String qp10VqseNaissance) {
        this.qp10VqseNaissance = qp10VqseNaissance;
    }

    public String getQp10PaysNaissance() {
        return qp10PaysNaissance;
    }

    public void setQp10PaysNaissance(String qp10PaysNaissance) {
        this.qp10PaysNaissance = qp10PaysNaissance;
    }

    public Short getQp11PeriodeResidence() {
        return qp11PeriodeResidence;
    }

    public void setQp11PeriodeResidence(Short qp11PeriodeResidence) {
        this.qp11PeriodeResidence = qp11PeriodeResidence;
    }

    public Short getQp12DomicileAvantRecensement() {
        return qp12DomicileAvantRecensement;
    }

    public void setQp12DomicileAvantRecensement(Short qp12DomicileAvantRecensement) {
        this.qp12DomicileAvantRecensement = qp12DomicileAvantRecensement;
    }

    public String getQp12CommuneDomicileAvantRecensement() {
        return qp12CommuneDomicileAvantRecensement;
    }

    public void setQp12CommuneDomicileAvantRecensement(String qp12CommuneDomicileAvantRecensement) {
        this.qp12CommuneDomicileAvantRecensement = qp12CommuneDomicileAvantRecensement;
    }

    public String getQp12VqseDomicileAvantRecensement() {
        return qp12VqseDomicileAvantRecensement;
    }

    public void setQp12VqseDomicileAvantRecensement(String qp12VqseDomicileAvantRecensement) {
        this.qp12VqseDomicileAvantRecensement = qp12VqseDomicileAvantRecensement;
    }

    public String getQp12PaysDomicileAvantRecensement() {
        return qp12PaysDomicileAvantRecensement;
    }

    public void setQp12PaysDomicileAvantRecensement(String qp12PaysDomicileAvantRecensement) {
        this.qp12PaysDomicileAvantRecensement = qp12PaysDomicileAvantRecensement;
    }

    public Short getQe1EstAlphabetise() {
        return qe1EstAlphabetise;
    }

    public void setQe1EstAlphabetise(Short qe1EstAlphabetise) {
        this.qe1EstAlphabetise = qe1EstAlphabetise;
    }

    public Short getQe2FreqentationScolaireOuUniv() {
        return qe2FreqentationScolaireOuUniv;
    }

    public void setQe2FreqentationScolaireOuUniv(Short qe2FreqentationScolaireOuUniv) {
        this.qe2FreqentationScolaireOuUniv = qe2FreqentationScolaireOuUniv;
    }

    public Short getQe3typeEcoleOuUniv() {
        return qe3typeEcoleOuUniv;
    }

    public void setQe3typeEcoleOuUniv(Short qe3typeEcoleOuUniv) {
        this.qe3typeEcoleOuUniv = qe3typeEcoleOuUniv;
    }

    public Short getQe4aNiveauEtude() {
        return qe4aNiveauEtude;
    }

    public void setQe4aNiveauEtude(Short qe4aNiveauEtude) {
        this.qe4aNiveauEtude = qe4aNiveauEtude;
    }

    public String getQe4bDerniereClasseOUAneEtude() {
        return qe4bDerniereClasseOUAneEtude;
    }

    public void setQe4bDerniereClasseOUAneEtude(String qe4bDerniereClasseOUAneEtude) {
        this.qe4bDerniereClasseOUAneEtude = qe4bDerniereClasseOUAneEtude;
    }

    public Short getQe5DiplomeUniversitaire() {
        return qe5DiplomeUniversitaire;
    }

    public void setQe5DiplomeUniversitaire(Short qe5DiplomeUniversitaire) {
        this.qe5DiplomeUniversitaire = qe5DiplomeUniversitaire;
    }

    public String getQe6DomaineEtudeUniversitaire() {
        return qe6DomaineEtudeUniversitaire;
    }

    public void setQe6DomaineEtudeUniversitaire(String qe6DomaineEtudeUniversitaire) {
        this.qe6DomaineEtudeUniversitaire = qe6DomaineEtudeUniversitaire;
    }

    public Short getQaf1HandicapVoir() {
        return qaf1HandicapVoir;
    }

    public void setQaf1HandicapVoir(Short qaf1HandicapVoir) {
        this.qaf1HandicapVoir = qaf1HandicapVoir;
    }

    public Short getQaf2HandicapEntendre() {
        return qaf2HandicapEntendre;
    }

    public void setQaf2HandicapEntendre(Short qaf2HandicapEntendre) {
        this.qaf2HandicapEntendre = qaf2HandicapEntendre;
    }

    public Short getQaf3HandicapMarcher() {
        return qaf3HandicapMarcher;
    }

    public void setQaf3HandicapMarcher(Short qaf3HandicapMarcher) {
        this.qaf3HandicapMarcher = qaf3HandicapMarcher;
    }

    public Short getQaf4HandicapSouvenir() {
        return qaf4HandicapSouvenir;
    }

    public void setQaf4HandicapSouvenir(Short qaf4HandicapSouvenir) {
        this.qaf4HandicapSouvenir = qaf4HandicapSouvenir;
    }

    public Short getQaf5HandicapPourSeSoigner() {
        return qaf5HandicapPourSeSoigner;
    }

    public void setQaf5HandicapPourSeSoigner(Short qaf5HandicapPourSeSoigner) {
        this.qaf5HandicapPourSeSoigner = qaf5HandicapPourSeSoigner;
    }

    public Short getQaf6HandicapCommuniquer() {
        return qaf6HandicapCommuniquer;
    }

    public void setQaf6HandicapCommuniquer(Short qaf6HandicapCommuniquer) {
        this.qaf6HandicapCommuniquer = qaf6HandicapCommuniquer;
    }

    public Short getQt1PossessionTelCellulaire() {
        return qt1PossessionTelCellulaire;
    }

    public void setQt1PossessionTelCellulaire(Short qt1PossessionTelCellulaire) {
        this.qt1PossessionTelCellulaire = qt1PossessionTelCellulaire;
    }

    public Short getQt2UtilisationInternet() {
        return qt2UtilisationInternet;
    }

    public void setQt2UtilisationInternet(Short qt2UtilisationInternet) {
        this.qt2UtilisationInternet = qt2UtilisationInternet;
    }

    public Short getQem1DejaVivreAutrePays() {
        return qem1DejaVivreAutrePays;
    }

    public void setQem1DejaVivreAutrePays(Short qem1DejaVivreAutrePays) {
        this.qem1DejaVivreAutrePays = qem1DejaVivreAutrePays;
    }

    public String getQem1AutrePays() {
        return qem1AutrePays;
    }

    public void setQem1AutrePays(String qem1AutrePays) {
        this.qem1AutrePays = qem1AutrePays;
    }

    public Short getQem2MoisRetour() {
        return qem2MoisRetour;
    }

    public void setQem2MoisRetour(Short qem2MoisRetour) {
        this.qem2MoisRetour = qem2MoisRetour;
    }

    public Integer getQem2AnneeRetour() {
        return qem2AnneeRetour;
    }

    public void setQem2AnneeRetour(Integer qem2AnneeRetour) {
        this.qem2AnneeRetour = qem2AnneeRetour;
    }

    public Short getQsm1StatutMatrimonial() {
        return qsm1StatutMatrimonial;
    }

    public void setQsm1StatutMatrimonial(Short qsm1StatutMatrimonial) {
        this.qsm1StatutMatrimonial = qsm1StatutMatrimonial;
    }

    public Short getQa1ActEconomiqueDerniereSemaine() {
        return qa1ActEconomiqueDerniereSemaine;
    }

    public void setQa1ActEconomiqueDerniereSemaine(Short qa1ActEconomiqueDerniereSemaine) {
        this.qa1ActEconomiqueDerniereSemaine = qa1ActEconomiqueDerniereSemaine;
    }

    public Short getQa2ActAvoirDemele1() {
        return qa2ActAvoirDemele1;
    }

    public void setQa2ActAvoirDemele1(Short qa2ActAvoirDemele1) {
        this.qa2ActAvoirDemele1 = qa2ActAvoirDemele1;
    }

    public Short getQa2ActDomestique2() {
        return qa2ActDomestique2;
    }

    public void setQa2ActDomestique2(Short qa2ActDomestique2) {
        this.qa2ActDomestique2 = qa2ActDomestique2;
    }

    public Short getQa2ActCultivateur3() {
        return qa2ActCultivateur3;
    }

    public void setQa2ActCultivateur3(Short qa2ActCultivateur3) {
        this.qa2ActCultivateur3 = qa2ActCultivateur3;
    }

    public Short getQa2ActAiderParent4() {
        return qa2ActAiderParent4;
    }

    public void setQa2ActAiderParent4(Short qa2ActAiderParent4) {
        this.qa2ActAiderParent4 = qa2ActAiderParent4;
    }

    public Short getQa2ActAutre5() {
        return qa2ActAutre5;
    }

    public void setQa2ActAutre5(Short qa2ActAutre5) {
        this.qa2ActAutre5 = qa2ActAutre5;
    }

    public Short getQa3StatutEmploie() {
        return qa3StatutEmploie;
    }

    public void setQa3StatutEmploie(Short qa3StatutEmploie) {
        this.qa3StatutEmploie = qa3StatutEmploie;
    }

    public Short getQa4SecteurInstitutionnel() {
        return qa4SecteurInstitutionnel;
    }

    public void setQa4SecteurInstitutionnel(Short qa4SecteurInstitutionnel) {
        this.qa4SecteurInstitutionnel = qa4SecteurInstitutionnel;
    }

    public String getQa5TypeBienProduitParEntreprise() {
        return qa5TypeBienProduitParEntreprise;
    }

    public void setQa5TypeBienProduitParEntreprise(String qa5TypeBienProduitParEntreprise) {
        this.qa5TypeBienProduitParEntreprise = qa5TypeBienProduitParEntreprise;
    }

    public String getQa5PreciserTypeBienProduitParEntreprise() {
        return qa5PreciserTypeBienProduitParEntreprise;
    }

    public void setQa5PreciserTypeBienProduitParEntreprise(String qa5PreciserTypeBienProduitParEntreprise) {
        this.qa5PreciserTypeBienProduitParEntreprise = qa5PreciserTypeBienProduitParEntreprise;
    }

    public Short getQa6LieuActDerniereSemaine() {
        return qa6LieuActDerniereSemaine;
    }

    public void setQa6LieuActDerniereSemaine(Short qa6LieuActDerniereSemaine) {
        this.qa6LieuActDerniereSemaine = qa6LieuActDerniereSemaine;
    }

    public Short getQa7FoncTravail() {
        return qa7FoncTravail;
    }

    public void setQa7FoncTravail(Short qa7FoncTravail) {
        this.qa7FoncTravail = qa7FoncTravail;
    }

    public Short getQa8EntreprendreDemarcheTravail() {
        return qa8EntreprendreDemarcheTravail;
    }

    public void setQa8EntreprendreDemarcheTravail(Short qa8EntreprendreDemarcheTravail) {
        this.qa8EntreprendreDemarcheTravail = qa8EntreprendreDemarcheTravail;
    }

    public Short getQa9VouloirTravailler() {
        return qa9VouloirTravailler;
    }

    public void setQa9VouloirTravailler(Short qa9VouloirTravailler) {
        this.qa9VouloirTravailler = qa9VouloirTravailler;
    }

    public Short getQa10DisponibilitePourTravail() {
        return qa10DisponibilitePourTravail;
    }

    public void setQa10DisponibilitePourTravail(Short qa10DisponibilitePourTravail) {
        this.qa10DisponibilitePourTravail = qa10DisponibilitePourTravail;
    }

    public Short getQa11RecevoirTransfertArgent() {
        return qa11RecevoirTransfertArgent;
    }

    public void setQa11RecevoirTransfertArgent(Short qa11RecevoirTransfertArgent) {
        this.qa11RecevoirTransfertArgent = qa11RecevoirTransfertArgent;
    }

    public Integer getQf1aNbreEnfantNeVivantM() {
        return qf1aNbreEnfantNeVivantM;
    }

    public void setQf1aNbreEnfantNeVivantM(Integer qf1aNbreEnfantNeVivantM) {
        this.qf1aNbreEnfantNeVivantM = qf1aNbreEnfantNeVivantM;
    }

    public Integer getQf1bNbreEnfantNeVivantF() {
        return qf1bNbreEnfantNeVivantF;
    }

    public void setQf1bNbreEnfantNeVivantF(Integer qf1bNbreEnfantNeVivantF) {
        this.qf1bNbreEnfantNeVivantF = qf1bNbreEnfantNeVivantF;
    }

    public Integer getQf2aNbreEnfantVivantM() {
        return qf2aNbreEnfantVivantM;
    }

    public void setQf2aNbreEnfantVivantM(Integer qf2aNbreEnfantVivantM) {
        this.qf2aNbreEnfantVivantM = qf2aNbreEnfantVivantM;
    }

    public Integer getQf2bNbreEnfantVivantF() {
        return qf2bNbreEnfantVivantF;
    }

    public void setQf2bNbreEnfantVivantF(Integer qf2bNbreEnfantVivantF) {
        this.qf2bNbreEnfantVivantF = qf2bNbreEnfantVivantF;
    }

    public Short getQf3DernierEnfantJour() {
        return qf3DernierEnfantJour;
    }

    public void setQf3DernierEnfantJour(Short qf3DernierEnfantJour) {
        this.qf3DernierEnfantJour = qf3DernierEnfantJour;
    }

    public Short getQf3DernierEnfantMois() {
        return qf3DernierEnfantMois;
    }

    public void setQf3DernierEnfantMois(Short qf3DernierEnfantMois) {
        this.qf3DernierEnfantMois = qf3DernierEnfantMois;
    }

    public Integer getQf3DernierEnfantAnnee() {
        return qf3DernierEnfantAnnee;
    }

    public void setQf3DernierEnfantAnnee(Integer qf3DernierEnfantAnnee) {
        this.qf3DernierEnfantAnnee = qf3DernierEnfantAnnee;
    }

    public Short getQf4DENeVivantVit() {
        return qf4DENeVivantVit;
    }

    public void setQf4DENeVivantVit(Short qf4DENeVivantVit) {
        this.qf4DENeVivantVit = qf4DENeVivantVit;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    public Boolean getIsFieldAllFilled() {
        return isFieldAllFilled;
    }

    public void setIsFieldAllFilled(Boolean isFieldAllFilled) {
        this.isFieldAllFilled = isFieldAllFilled;
    }

    public String getDateDebutCollecte() {
        return dateDebutCollecte;
    }

    public void setDateDebutCollecte(String dateDebutCollecte) {
        this.dateDebutCollecte = dateDebutCollecte;
    }

    public String getDateFinCollecte() {
        return dateFinCollecte;
    }

    public void setDateFinCollecte(String dateFinCollecte) {
        this.dateFinCollecte = dateFinCollecte;
    }

    public Integer getDureeSaisie() {
        return dureeSaisie;
    }

    public void setDureeSaisie(Integer dureeSaisie) {
        this.dureeSaisie = dureeSaisie;
    }

    public Boolean getIsContreEnqueteMade() {
        return isContreEnqueteMade;
    }

    public void setIsContreEnqueteMade(Boolean isContreEnqueteMade) {
        this.isContreEnqueteMade = isContreEnqueteMade;
    }

    public String getCodeAgentRecenceur() {
        return codeAgentRecenceur;
    }

    public void setCodeAgentRecenceur(String codeAgentRecenceur) {
        this.codeAgentRecenceur = codeAgentRecenceur;
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return "[" + this.q1NoOrdre.toString() +"] "  + this.qp2APrenom.toString() +" "+ this.qp2BNom.toString().toUpperCase();
    }
    //endregion

    //region Model getters and setters
    public MenageModel getObjMenage() {
        return objMenage;
    }

    public void setObjMenage(MenageModel objMenage) {
        this.objMenage = objMenage;
    }

    public LogementModel getObjLogement() {
        return objLogement;
    }

    public void setObjLogement(LogementModel objLogement) {
        this.objLogement = objLogement;
    }

    public BatimentModel getObjBatiment() {
        return objBatiment;
    }

    public void setObjBatiment(BatimentModel objBatiment) {
        this.objBatiment = objBatiment;
    }

    public boolean getIsAgeIndividuVerify() {
        return IsAgeIndividuVerify;
    }

    public void setIsAgeIndividuVerify(boolean IsAgeIndividuVerify) {
        this.IsAgeIndividuVerify = IsAgeIndividuVerify;
    }
    //endregion

    //region METHODES
    public static void Check_ContrainteSautChampsValeur(IndividuModel individuModel)  throws TextEmptyException {
        try {
            //region P1A-P12.2 "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
            //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            //if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp3LienDeParente.columnName)){
                /* Le numéro d’ordre doit être unique.
                    Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                    Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
            Boolean isMounNanMenajLa = false;
            if( individuModel.getQp3HabiteDansMenage()==1 ||
                    individuModel.getQp3HabiteDansMenage()==3 ||
                    individuModel.getQp3HabiteDansMenage()==5 ||
                    individuModel.getQp3HabiteDansMenage()==6 ||
                    individuModel.getQp3HabiteDansMenage()==7 ) {
                isMounNanMenajLa=true;
            }
            if ( !isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye.\n Ou dwe antre Chèf menaj la avan!");
            }

            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                    individuModel.getQp3LienDeParente() > Constant.Chef_menaj_la_01) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                    individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01) {
                throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                    individuModel.getQp5bAge() < Constant.AGE_15ANS) {
                throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                        + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                        + " ki chef menaj lan. ");
            }
            //}
            //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            //if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp3LienDeParente.columnName) ) {
            /* Le numéro d’ordre doit être unique.
                Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
            //if (  isMounNanMenajLa && individuModel.getObjLogement() != null ) {
                // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                    individuModel.getQp3LienDeParente() > Constant.Chef_menaj_la_01 ) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                    individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 ) {
                throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
            }
            //}
        //}
        // P4 - ÈSKE {0} SE  ?  [  ]
        //if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp4Sexe.columnName)){
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2) {
                // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                IndividuModel individuCM = null;
                if ( individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                }

                if (individuCM == null) {
                    throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                } else {
                        /*  Si P4= 1 pour P3 = 01 alors P4 = 2 pour P3 = 02
                            Si P4=2  pour P3 = 01 alors P4= 1 pour P3 = 02
                            Dans le cas où le chef de ménage a un conjoint dans le ménage. Le sexe du chef de ménage et celui de son conjoint doit être différent */
                        /* Si P4= 1 pour P3 = 01 alors P4 = 1 pour P3 = 02 , de même si P4=2 pour P3=01 alors P4=2 pour P3=02, afficher un message d’erreur et permettre de vérifier l’information */
                    if (individuCM.getQp4Sexe() == individuModel.getQp4Sexe() &&
                            individuModel.getQp3LienDeParente() == Constant.Mari_Madanm_02) {
                        throw new TextEmptyException("Chef menaj la [ " + individuCM.getQp2BNom() + " " + individuCM.getQp2APrenom() + " ] paka gen menm sèks ak konjwen li  [ "
                                + individuModel.getQp2BNom() + " " + individuModel.getQp2APrenom() + " ].");
                    }
                }
            //}
            //}

            //region Etablir une relation entre l’âge du CM et celui de son (sa) fils/fille (Père/Mère).
            //if (individuModel.getQ1NoOrdre() >= 2) {
               /* // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                IndividuModel individuCM = null;
                if (individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                }*/

                if (individuCM == null) {
                    throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                } else {
                    int differenceceAge = 0;
                    String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                    String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                    //#00 Verifye si chef menaj la pi gran ke papa li oswa manman li
                    /*if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp3LienDeParente() == Constant.Papa_Manman_06
                            && individuCM.getQp5bAge() > individuModel.getQp5bAge() ) {
                        throw new TextEmptyException("Atansyon, verifye laj [ " + NomCompletInd + " ], ou di li genyen [ " + individuModel.getQp5bAge() + " ] ane.\n"
                                + "Li pa dwe pi gran ke Chef Menaj la [ " + NomCompletCM + " ] ki genyen [ " + individuCM.getQp5bAge() + " ] ane." );
                    }*/
                    //#1 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >13 ans. Dans le cas contraire voir message
                    //differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );
                    if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                            individuModel.getQp3LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03 ){

                        differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                        if ( differenceceAge <= Constant.AGE_13ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                        }
                    }
                    //#2 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >15 ans
                    if (individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1
                            && individuModel.getQp3LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03) {

                        differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                        if ( differenceceAge <= Constant.AGE_15ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                        }
                    }

                    //#3 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07) est >28 ans
                    if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                            individuModel.getQp3LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                        differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                        if( differenceceAge <= Constant.AGE_28ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_28ANS + " zan");
                        }
                    }
                    //#4 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07)  est >32 ans
                    if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1 &&
                            individuModel.getQp3LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                        differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                        if(  differenceceAge <= Constant.AGE_32ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_32ANS + " zan");
                        }
                    }
                    //#5 Si CM (P3=1) est une femme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage (P3=1)   est >13 ans
                    if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2
                            && individuModel.getQp3LienDeParente() == Constant.Papa_Manman_06 ){

                        differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );

                        if(  differenceceAge <= Constant.AGE_13ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                        }
                    }
                    //#6 Si CM (P3=1) est un homme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage est >15 ans
                    if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1
                            && individuModel.getQp3LienDeParente() == Constant.Papa_Manman_06){

                        differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );

                        if(  differenceceAge <= Constant.AGE_15ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                        }
                    }
                }

            }
                    /*//#1 Si CM est une femme alors la différence d'âge entre le CM et le (la) fils/fille est >13 ans
                    //#2 Si CM est un  homme alors la différence d'âge entre le CM et le (la) fils/fille est >15 ans
                    //#3 Si CM est une femme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >28 ans
                    //#4 Si CM est un homme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >32 ans
                    //#5 Si CM est une femme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >13 ans
                    //#6 Si CM est un homme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >15 ans
                    // (même remarque pour le conjoint et père du conjoint=15 et conjoint et mère du conjoint=13) */

            //endregion

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String Check_ContrainteSautChampsValeur(String nomChamps, IndividuModel individuModel, Long iDKeys, Object dataBase, Boolean ExpulseException)  throws TextEmptyException  {
        try {
            String QSuivant = "";
            //region P1A-P12.2 "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
            if ( individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp2BNom.columnName) ) {
                    individuModel.IsAgeIndividuVerify=true;
                    QSuivant = "P3.1";
                }
            }
            //P3.1	Eske se isit la (....) Toujou rete sa vle di se la li dómi, manje avèk tout lót moun ?
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp3HabiteDansMenage.columnName) ) {
                individuModel.IsAgeIndividuVerify=true;
                Boolean isMounNanMenajLa = false;
                if( individuModel.getQp3HabiteDansMenage()!=null && individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL){
                    if( individuModel.getQp3HabiteDansMenage()==1 ||
                            individuModel.getQp3HabiteDansMenage()==3 ||
                            individuModel.getQp3HabiteDansMenage()==5 ||
                            individuModel.getQp3HabiteDansMenage()==6 ||
                            individuModel.getQp3HabiteDansMenage()==7) {
                        isMounNanMenajLa=true;
                    }
                }

                if ( !isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye.\n Ou dwe antre Chèf menaj la avan!");
                }

                if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                        individuModel.getQp3LienDeParente() > Constant.Chef_menaj_la_01 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                }
                if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                        individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                }
                if ( isMounNanMenajLa && individuModel.getQp3LienDeParente()!=null &&
                        individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01
                        && individuModel.getQp5bAge()!=null &&  individuModel.getQp5bAge() <= Constant.AGE_15ANS
                        && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                        && individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                    throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                            + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                            + " ki chef menaj lan. ");
                }
            }
                    //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp3LienDeParente.columnName) ) {
                individuModel.IsAgeIndividuVerify=true;
            /* Le numéro d’ordre doit être unique.
                Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() <= 1 &&
                            individuModel.getQp3LienDeParente() > Constant.Chef_menaj_la_01) {
                        throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                    }
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2 &&
                            individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01) {
                        throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                    }
                }
            }
            // P4 - ÈSKE {0} SE  ?  [  ]
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp4Sexe.columnName) ) {
                String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                individuModel.IsAgeIndividuVerify=true;

                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        IndividuModel individuCM = null;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }

                        if ( ExpulseException==true && individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {
                            String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                        /*  Si P4= 1 pour P3 = 01 alors P4 = 2 pour P3 = 02
                            Si P4=2  pour P3 = 01 alors P4= 1 pour P3 = 02
                            Dans le cas où le chef de ménage a un conjoint dans le ménage. Le sexe du chef de ménage et celui de son conjoint doit être différent */
                        /* Si P4= 1 pour P3 = 01 alors P4 = 1 pour P3 = 02 , de même si P4=2 pour P3=01 alors P4=2 pour P3=02, afficher un message d’erreur et permettre de vérifier l’information */
                            if ( ExpulseException==true && individuCM.getQp4Sexe() == individuModel.getQp4Sexe() &&
                                    individuModel.getQp3LienDeParente() == Constant.Mari_Madanm_02) {
                                throw new TextEmptyException("Chef menaj la [ " + NomCompletCM + " ] paka gen menm sèks ak konjwen li  [ "
                                        + NomCompletInd + " ].");
                            }
                        }
                    }
                }
            }

            //region P5.A - Ki dat {0} te fet ?  [  ]
            // endregion

            //region P5 - KI LAJ {0} GENYEN ? [  ]
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp5bAge.columnName)) {
                int annee =0;
                annee = individuModel.getQp5DateNaissanceAnnee();
                Calendar mydate = new GregorianCalendar();
                int anneeSysteme = mydate.get(Calendar.YEAR);
                String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();

               if ( ExpulseException==true && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS ) { // Si moun nan konn ane , si li pa 9999
                    if ( individuModel.getQp5bAge() > Constant.AGE_120ANS ) {
                        individuModel.IsAgeIndividuVerify=true;
                        throw new TextEmptyException("Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ");
                    }

                    int ageDiff = ( anneeSysteme - annee );
                    if ( annee != Constant.ANNEE_PA_KONNEN_9999ANS ) { // Si moun nan konn ane , si li pa 9999
                        if( individuModel.IsAgeIndividuVerify ) {
                            if ( individuModel.getQp5bAge() != ageDiff) {
                                individuModel.IsAgeIndividuVerify=false;
                                String _message = "Atansyon verifye laj ou antre a ak dènye lè ["+ NomCompletInd +"] te fete anivèsè li.";
                                _message += "\n\nPaske pou systèm nan moun sa ta dwe genyen [ " + ageDiff + " ] ane si li fèt nan ane [ " + annee + " ]";
                                _message +=  "\n\nTandiske w antre [ " + individuModel.getQp5bAge() + " ] ane pou moun sa a.";
                                _message += "\n\nSi w vle kite l konsa retouche bouton [Kontinye] a.";
                              throw new TextEmptyException(_message);
                            }
                        }
                    }
                }
                //QF.CheckAgeDialog(sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
            }//endregion

            if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                    individuModel.getObjLogement().getQlCategLogement() != null &&
                    individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                //P5 - KI LAJ {0} GENYEN ? [  ]
                if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp5bAge.columnName)) {
                /* Permettre que P3 = « 01 » est toujours attribué au chef de ménage et égal à son numéro d’ordre P1 = « 01 ».


                    Etablir une relation entre P3 et P5.
                    Si P3=01 alors P5≥15 ans
                    Si P.3 = 01 et P.5 ≤ 15 ans, afficher un message d’erreur permettant de vérifier les informations */
                    if ( ExpulseException==true && individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            individuModel.getQp5bAge() < Constant.AGE_15ANS ) {
                        throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                                + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                                + " ki chef menaj lan. ");
                    }

                    //region Etablir une relation entre l’âge du CM et celui de son (sa) fils/fille (Père/Mère).
                    if (individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        IndividuModel individuCM = null;
                        if (individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                        } //else if (individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                        //    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        //}

                        if (individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {
                            int differenceceAge = 0;
                            String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                            String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                            //#1 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >13 ans. Dans le cas contraire voir message
                            //differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );
                            if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                                    individuModel.getQp3LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03 ){

                                differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                                if ( differenceceAge <= Constant.AGE_13ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                                }
                            }
                            //#2 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >15 ans
                            if (individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1
                                    && individuModel.getQp3LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03) {

                                differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                                if ( differenceceAge <= Constant.AGE_15ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                                }
                            }
                            //#3 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07) est >28 ans
                            if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                                    individuModel.getQp3LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                                differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                                if( differenceceAge <= Constant.AGE_28ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_28ANS + " zan");
                                }
                            }
                            //#4 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07)  est >32 ans
                            if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1 &&
                                    individuModel.getQp3LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07){

                                differenceceAge = ( individuCM.getQp5bAge() - individuModel.getQp5bAge() );

                                if(  differenceceAge <= Constant.AGE_32ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_32ANS + " zan");
                                }
                            }
                            //#5 Si CM (P3=1) est une femme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage (P3=1)   est >13 ans
                            if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2
                                    && individuModel.getQp3LienDeParente() == Constant.Papa_Manman_06 ){

                                differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );

                                if(  differenceceAge <= Constant.AGE_13ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                                }
                            }
                            //#6 Si CM (P3=1) est un homme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage est >15 ans
                            if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1
                                    && individuModel.getQp3LienDeParente() == Constant.Papa_Manman_06){

                                differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );

                                if(  differenceceAge <= Constant.AGE_15ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQp5bAge() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQp5bAge() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                                }
                            }
                        }

                    }
                    /*//#1 Si CM est une femme alors la différence d'âge entre le CM et le (la) fils/fille est >13 ans
                    //#2 Si CM est un  homme alors la différence d'âge entre le CM et le (la) fils/fille est >15 ans
                    //#3 Si CM est une femme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >28 ans
                    //#4 Si CM est un homme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >32 ans
                    //#5 Si CM est une femme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >13 ans
                    //#6 Si CM est un homme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >15 ans
                    // (même remarque pour le conjoint et père du conjoint=15 et conjoint et mère du conjoint=13) */

                //endregion

                }
                //region P8 - ÈSKE MANMAN {0} AP VIV TOUJOU ?     [  ]
                if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp8MereEncoreVivante.columnName)) {
                /* Si fils/fille (P3=03) d’un chef de ménage (P3 =01) de sexe féminin (P4= 02) alors P.8 = 1 */
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        IndividuModel individuCM = null;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }

                        if ( ExpulseException==true && individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {
                            if ( ExpulseException==true && individuCM.getQp4Sexe() == Constant.FEMME_2 && individuModel.getQp3LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03
                                    && individuModel.getQp8MereEncoreVivante() != Constant.REPONS_WI_1) {
                                throw new TextEmptyException("Ou pa dwe chwazi [ "
                                        + Tools.getString_Reponse("" + individuModel.getQp8MereEncoreVivante(), IndividuDao.Properties.Qp8MereEncoreVivante.columnName) + " ] "
                                        + " \n Paske manmanl se Chef menaj la li ye.");
                            }
                        }
                    }
                }//endregion
            }
            //region P12
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp12DomicileAvantRecensement.columnName)){
                /* Etablir une relation entre P.5 et P.12
                Si P.5 < 3 ans et P.12 = 4, permettre d'aller au statut de remplissage du questionnaire
                c'est-à-dire à la section relative au rapport de l'Agent Recenseur avant de mettre fin
                si c’est la dernière personne de la liste des membres du ménage. Ou bien, de passer au remplissage de la fiche individuelle pour une autre personne
                si ce n’est pas la dernière personne de la liste des membres du ménage.
                 */
                Calendar mydate = new GregorianCalendar();
                //mydate.setTime(new Date());
                //int anneSysteme = mydate.get(Calendar.YEAR);
                //int _5AneApreResansman = Constant.ANE_RESANSMAN-5;
                //int _AgeATester = (anneSysteme-_5AneApreResansman);

               /*if(individuModel.getQp5bAge() <= _AgeATester
                        && individuModel.getQp12DomicileAvantRecensement() !=  Constant.Pa_Aplikab_4){
                    throw new TextEmptyException("Repons ou chwazi a pa bon.\n\nPa aplikab (Tout moun ki fèt aprè "+  +") = " + Annee2012);
                }*/
                if( individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                        &&  individuModel.getQp5bAge() < Constant.AGE_03ANS ){
                    if( individuModel.getQp12DomicileAvantRecensement() == Constant.R05_Non_Aplikab_05 ) {
                        QSuivant = Constant.FIN;
                    }else if( individuModel.getQp12DomicileAvantRecensement() != Constant.R05_Non_Aplikab_05 ) {
                        throw new TextEmptyException("Repons ou chwazi a pa bon.\nPa aplikab");
                    }
                }
                /* Si 3 ans ≤ P.5.B < 5 ans et P.12 = 5, permettre de passer au remplissage de la section suivante (E.- Education) plus particulièrement en E.2. */
                if(  individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                        && individuModel.getQp5bAge() >= Constant.AGE_03ANS
                        && individuModel.getQp5bAge() <  Constant.AGE_05ANS ){
                    if( individuModel.getQp12DomicileAvantRecensement() == Constant.R05_Non_Aplikab_05 ) {
                        QSuivant = "E2";
                    }else if( individuModel.getQp12DomicileAvantRecensement() != Constant.R05_Non_Aplikab_05 ) {
                        throw new TextEmptyException("Repons ou chwazi a pa bon.\nPa aplikab");
                    }
                }
            }//endregion
            //region P12.1
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp12DomicileAvantRecensement.columnName)){
                /* KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS */
                if(individuModel.getQp5bAge() < Constant.AGE_03ANS ){
                    QSuivant =  Constant.FIN;
                }
            }//endregion
            //region P12.2
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp12PaysDomicileAvantRecensement.columnName)){
                /* KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS */
                if(individuModel.getQp5bAge() < Constant.AGE_03ANS ){
                    QSuivant =  Constant.FIN;
                }
            }//endregion
            //endregion

            //region E1-E8 KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS */
            //region E1 	- ESKE {0} KONN LI AK EKRI ?
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe1EstAlphabetise.columnName)){
                /* Introduire un contrôle permettant de vérifier que P.5≥3 ans
                    Si E.1=7 et que P.5  <6 ans, introduire un  saut permettant d'aller à la question suivante. */
                if( ExpulseException==true && individuModel.getQp5bAge() < Constant.AGE_06ANS && individuModel.getQe1EstAlphabetise() !=  Constant.Pa_Aplikab_7){
                    throw new TextEmptyException("Pa aplikab (Tout moun ki gen pi piti ke 6 lane)");
                }
            }//endregion
            //region E2  -	PANDAN DÈNYE LANE LEKOL LA (2016-2017), ESKE {0}  TE  ALE LEKÒL / INIVÈSITE
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe2FreqentationScolaireOuUniv.columnName)){
                 /*Introduire un contrôle permettant de vérifier l’âge de la personne avant la saisie des données.
                 Si P5<3 ans, fin du remplissage de la fiche individuelle pour cette personne
                 Si E.2 ≥ 7, introduire un contrôle de saut permettant d’aller automatiquement à E.4.A */
                String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                 //String message = "Repons la pa dwe [ "+ Tools.getString_Reponse("" + individuModel.getQe2FreqentationScolaireOuUniv(), IndividuDao.Properties.Qe2FreqentationScolaireOuUniv.columnName) +" ] pou moun ki gen " +  individuModel.getQp5bAge() + " lane";
                if( ExpulseException==true && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS ) {
                    /* E2=1 : 3 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_03ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R01_Wi_Preskole_1 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=2 : 5 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_05ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R02_Wi_Prime_Fondamantal_1e_sik_2 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=3 : 7 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_07ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R03_Wi_Prime_Fondamantal_2e_sik_3 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=4 : 10 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_10ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R04_Wi_Segonde_Fondamantal_3e_sik_4 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=5 : 14 ans */
                    if( individuModel.getQp5bAge() < Constant.AGE_14ANS) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R05_Wi_Segonde_3e_Filo_5 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=6 : 17 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_17ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R06_Wi_Inivesite_6 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                /* E2=7 : 10 ans */
                    if ( individuModel.getQp5bAge() < Constant.AGE_10ANS ) {
                        if ( individuModel.getQe2FreqentationScolaireOuUniv() == Constant.R07_Wi_Lekol_Pwofesyonel_7 ) {
                            throw new TextEmptyException("" + NomCompletInd + " Pa gen laj pou nivo sa. [ " + individuModel.getQp5bAge() + " ane ]");
                        }
                    }
                }

            }//endregion
            //region E4A Ki pi wo nivo {0} te rive nan lekol / inivesite?
            if( nomChamps.equalsIgnoreCase( Constant.Qe4ANiveauEtudeETClasse ) ){
                /* Ki denye klas oswa ki denye lane {0} te pase (reyisi) nan nivo sa a? */
                if(individuModel.getQp5bAge() < Constant.AGE_05ANS ){
                    /* KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE */
                    QSuivant =  Constant.FIN;
                }
            }//endregion
            // E4B
            /*if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe4bDerniereClasseOUAneEtude.columnName)){
                if(               //3. Preskolè ---- Ale nan E4B apre ale nan E7
                        ExpulseException==true &&   individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Preskole_3
                                ||//4. Primè / Fondamantal 1e sik ---- Ale nan E4B apre ale nan E7
                                individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Prime_Fondamantal_1e_sik_4
                                || //5. Primè / Fondamantal 2e sik ---- Ale nan E4B apre ale nan E7
                                individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Prime_Fondamantal_2e_sik_5
                                || // 6. Segondè  /  Fondamantal 3e sik ---- Ale nan E4B apre ale nan E7
                                individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Segonde_Fondamantal_3e_sik_6
                                || // 7. Segondè (3e – Filo) ---- Ale nan E4B apre ale nan E7
                                individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Segonde_3e_Filo_7){
                                //9. Pa konnen ---- Ale nan seksyon AF
                    QSuivant =  "AF1";
                }
                if(// 8. Inivèsite ---- Ale nan E4B apre ale nan E5
                        ExpulseException==true &&   individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Inivesite_8){
                    QSuivant =  "E5";
                }
            }*/
            //E5 - KI PI GWO DIPLÒM INIVÈSITÈ {0} GENYEN? [  ]
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe5DiplomeUniversitaire.columnName)){
            /* Introduire un contrôle établissant une relation entre E.4.A =8, E.4.B et E.5.
            Si l’une des modalités en E5 est cochée, cela signifie que cette personne en E4A a un niveau Universitaire pour cela a une réponse valide en E4B également sinon afficher un message d’erreur*/
             // GERER AU NIVEAU DE LA BASE...
            }
            // E6
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe6DomaineEtudeUniversitaire.columnName)){
                if(individuModel.getQp5bAge() < Constant.AGE_05ANS ){
                    /* KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE */
                    QSuivant =  Constant.FIN;
                }
            }
            // E7 DELETE
            /*if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qe7FormationProf.columnName)){
                *//* Etablir la relation entre
                - E.4.A et E.7 si E.4.A ≤ 2;
                - E.4.A, E.4.B et E.7 si E.4.A=3 ou 4 ou 5 ou 6 ou 7
                -E.4.A, E.4.B, E.5  et E.7 si E.4.A = 8 *//*
                if( individuModel.getQe4aNiveauEtude() ==  Constant.NiveauEtude_Segonde_3e_Filo_7){
                    // A EXPLIQUER
                }

                if(individuModel.getQp5bAge() <= Constant.AGE_05ANS && individuModel.getQe7FormationProf() ==  Constant.Non_5){
                    *//* KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE *//*
                    QSuivant =  Constant.FIN;
                }
                if(individuModel.getQp5bAge() >= Constant.AGE_05ANS && individuModel.getQe7FormationProf() ==  Constant.Non_5){
                    QSuivant = "AF1A";//5. Non -------Ale nan seksyon AF si P5 ≥ 5 lane
                }
            }*/
            //endregion

            // region AF6A-AF6B KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOMINIKATIF
            // AF6
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qaf6HandicapCommuniquer.columnName)){
                if(individuModel.getQp5bAge() < Constant.AGE_10ANS){
                    // 1. Non, okenn difikilte ----Ale nan T1
                    QSuivant =  Constant.FIN;
                }
            }
            //endregion

            //region T2 KARAKTERISTIK MOUN NAN -|- NOUVO TEKNOLOJI ENFOMASYON AK KOMINIKASYON(POU MOUN KI GEN 6 LANE OSWA PLIS) -|- ITILIZASYON  ENTENET AK AKSE
            // T2
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qt2UtilisationInternet.columnName)){
                if( individuModel.getQp5bAge() < Constant.AGE_10ANS){
                    QSuivant =  Constant.FIN;
                }
            }
            //endregion

            //region SM1 Pou moun ki genyen dis (10) lane epi plis -|- ESTATI MATRIMONYAL
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qsm1StatutMatrimonial.columnName) ){
                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT

                      /* TEST POU CM la selman */
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() == 1 ) {
                        int countNbrMadanmCM = 0;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            /* Verifye si CM la gen madan  deja */
                            countNbrMadanmCM = GetCountMarie_Ou_Madanm( Constant.Mari_Madanm_02, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);

                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            /* Verifye si CM la gen madan  deja */
                            countNbrMadanmCM = GetCountMarie_Ou_Madanm( Constant.Mari_Madanm_02, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }
                         /* Anpeche ke yo chwazi stati selibate=1 ak Veuf=5 pou CM la si li gen madanm ou mari */
                        if( countNbrMadanmCM > 0 ){
                            if ( ExpulseException==true
                                    && individuModel.getQsm1StatutMatrimonial() == Constant.Selibate_01  ) {
                                throw new TextEmptyException("Atansyon!!! Chèf menaj pa ka Selibatè.  \nPaske li genyen yon konjwen kap viv avèk li nan menaj la.");
                            }
                            if ( ExpulseException==true
                                    && individuModel.getQsm1StatutMatrimonial() == Constant.Veuf_05  ) {
                                throw new TextEmptyException("Atansyon!!! Chèf menaj pa ka Vèv \nPaske li genyen yon konjwen kap viv avèk li nan menaj la. ");
                            }
                        }
                    }
                /* Etablir la relation entre P.4, P.3, SM.1 */
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        IndividuModel individuCM = null;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);

                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }

                        if ( ExpulseException==true && individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {

                        /* Si P.4 = 2 pour P.3 = 01 et SM.1 = 2, 3 ou 4, alors P.4 = 1 pour P.3 =02 en admettant que le conjoint soit dans le ménage
                        Le statut matrimonial du Chef de ménage doit être le même que celui de son conjoint.
                        Afficher un  message d’erreur si SM.1 pour  P.3 = 01 ≠ SM.1 pour P3 = 02 et permettre à l’Agent Recenseur de vérifier et de corriger l’information enregistrée. */
                            if ( ExpulseException==true
                                    && individuCM.getQsm1StatutMatrimonial() != individuModel.getQsm1StatutMatrimonial()
                                    && individuModel.getQp3LienDeParente() == Constant.Mari_Madanm_02) {
                                throw new TextEmptyException("Chef menaj la fet pou gen menm estati matrimonyal ak konjwen li depi yo tou le de nan menaj la. \n\n"
                                        + "Ou te di Chef menaj la : [ " + Tools.getString_Reponse("" + individuCM.getQsm1StatutMatrimonial(), IndividuDao.Properties.Qsm1StatutMatrimonial.columnName) + " ] ");
                            }
                        }
                    }
                }
            }
            //endregion

            //region A1-A11 Pou moun ki genyen dis (10) lane epi plis -|- Aktivite Ekonomik
            //A2.5	PANDAN DÈNYE SEMÈN KI SOT PASE LA A , ÈSKE (…) TE FÈ YOUN NAN AKTIVITE SA YO?
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qa2ActAutre5.columnName)){
                /* Si pour toutes les activités on enregistre "Non" introduire alors un saut de contrôle pour permettre d'aller à A.8 */
                if( individuModel.getQa2ActAvoirDemele1()!= null && individuModel.getQa2ActAvoirDemele1() == Constant.REPONS_NON_2
                        && individuModel.getQa2ActDomestique2()!=null && individuModel.getQa2ActDomestique2() == Constant.REPONS_NON_2
                        && individuModel.getQa2ActCultivateur3()!=null && individuModel.getQa2ActCultivateur3() == Constant.REPONS_NON_2
                        && individuModel.getQa2ActAiderParent4()!=null && individuModel.getQa2ActAiderParent4() == Constant.REPONS_NON_2
                        && individuModel.getQa2ActAutre5()!=null && individuModel.getQa2ActAutre5() == Constant.REPONS_NON_2){
                    QSuivant =  "A8";
                }
            }

            // A11	PANDAN 12 DÈNYE MWA KI SOT PASE YO, ÈSKE {0} TE RESEVWA TRANSFÈ LAJAN?
            if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qa11RecevoirTransfertArgent.columnName)){
                /* Si se yon fi ki gen 13 lane oswa plis, ale nan seksyon F la.
                Si gen lòt moun nan menaj (lojman kolektif) la ke w poko gen enfomasyon pou yo, tounen nan komanseman seksyon CP a. */
                if(individuModel.getQp5bAge() >= Constant.AGE_13ANS && individuModel.getQp4Sexe() ==  Constant.FEMME_2 ){ }
                /*  Si se dènye moun nan menaj (lojman kolektif) la epi se pa yon fi 13 lane oswa plis ale nan Rapò Ajan Resansè a.*/
                if(individuModel.getQp5bAge() < Constant.AGE_13ANS || individuModel.getQp4Sexe() ==  Constant.HOMME_1 ){
                    QSuivant =  Constant.FIN;
                }
            }
            //endregion

            //region F1A-F5 : FEGONDITE -|- POU FANM KI GEN 13 LANE OSWA PLIS
            //F1B	KONBYEN  TIMOUN ANTOU {0} FE TOU VIVAN?
            if ( nomChamps.equalsIgnoreCase( Constant.Qf1NbreEnfantNeVivantGarconEtFille )){
                int NbrEnfantMasculin = ( individuModel.getQf1aNbreEnfantNeVivantM() != Constant.REPONS_PA_KONNEN_99 ? individuModel.getQf1aNbreEnfantNeVivantM():0 );
                int NbrEnfantFeminin = ( individuModel.getQf1bNbreEnfantNeVivantF() != Constant.REPONS_PA_KONNEN_99 ? individuModel.getQf1bNbreEnfantNeVivantF():0 );
                int NbrTotalEnfant = 0;
                /* Si F.1.A et F.1.B=00 ou Si F.1.A et F.1.B=99 permettre d'aller automatiquement à une autre personne ou mettre fin au questionnaire */
                if( ExpulseException==true
                        && (individuModel.getQf1aNbreEnfantNeVivantM() == Constant.REPONS_PA_KONNEN_99
                        && individuModel.getQf1bNbreEnfantNeVivantF() == Constant.REPONS_PA_KONNEN_99 ) ||
                    (individuModel.getQf1aNbreEnfantNeVivantM() <= 0 && individuModel.getQf1bNbreEnfantNeVivantF() <= 0) ){
                    QSuivant =  Constant.FIN;
                }else{
                    String message = "";
                    NbrTotalEnfant = NbrEnfantMasculin + NbrEnfantFeminin;
                    /* Si le chef de ménage est une femme et son âge est compris entre 13 et 19 ans, le nombre total d’enfants nés vivants ne peut être supérieur à 3 enfants.
                    Si tel est le cas, bloquer l’accès et vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_13ANS && individuModel.getQp5bAge() <= Constant.AGE_19ANS
                            && NbrTotalEnfant > Constant.NBR_3_ENFANT ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_3_ENFANT +" timoun.");
                    }
                    /* Si le chef de ménage est une femme et son âge est compris entre 20 et 24 ans, le nombre total d’enfants nés vivants ne peut être supérieur à 6 enfants.
                    Si tel est le cas, bloquer l’accès et vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_20ANS && individuModel.getQp5bAge() <= Constant.AGE_24ANS
                            && NbrTotalEnfant > Constant.NBR_6_ENFANT ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_6_ENFANT +" timoun.");
                    }
                    /* Si le chef de ménage est une femme et son âge est compris entre 25 et 29 ans, le nombre total d’enfants nés vivants ne peut être supérieur à 8 enfants.
                    Si tel est le cas, bloquer l’accès et vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_25ANS && individuModel.getQp5bAge() <= Constant.AGE_29ANS
                            && NbrTotalEnfant >  Constant.NBR_8_ENFANT ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_8_ENFANT +" timoun.");
                    }
                    /* Si l’âge du Chef de ménage est compris entre 30 et 34, le nombre total d’enfants nés vivants ne peut être supérieur à 10 enfants.
                    Si tel est le cas, bloquer l’accès et  vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&  individuModel.getQp5bAge() >= Constant.AGE_30ANS && individuModel.getQp5bAge() <= Constant.AGE_34ANS
                            && NbrTotalEnfant > Constant.NBR_10_ENFANT  ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_10_ENFANT +" timoun.");
                    }
                    /* Si l’âge du Chef de ménage est compris entre 35 et 39, le nombre total d’enfants nés vivants ne peut être supérieur à 12 enfants.
                    Si tel est le cas, bloquer l’accès et  vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_35ANS && individuModel.getQp5bAge() <= Constant.AGE_39ANS
                            && NbrTotalEnfant >  Constant.NBR_12_ENFANT  ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_12_ENFANT +" timoun.");
                    }
                    /* Si l’âge du Chef de ménage est compris entre 40 et 44, le nombre total d’enfants nés vivants ne peut être supérieur à 14 enfants.
                    Si tel est le cas, bloquer l’accès et  vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_40ANS && individuModel.getQp5bAge() <= Constant.AGE_44ANS
                            && NbrTotalEnfant >  Constant.NBR_14_ENFANT  ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_14_ENFANT +" timoun.");
                    }
                    /* Si l’âge du Chef de ménage est compris entre 45 et 49, le nombre total d’enfants nés vivants ne peut être supérieur à 16 enfants.
                    Si tel est le cas, bloquer l’accès et  vérifier les informations enregistrées */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&   individuModel.getQp5bAge() >= Constant.AGE_45ANS && individuModel.getQp5bAge() <= Constant.AGE_49ANS
                            && NbrTotalEnfant > Constant.NBR_16_ENFANT  ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_16_ENFANT +" timoun.");
                    }
                    /* Si le chef de ménage est une femme et son âge est compris entre 45 et 49 ans, le nombre total d’enfants nés vivants ne peut être supérieur à 16 enfants.
                    Si tel est le cas, bloquer l’accès et vérifier les informations enregistrées. */
                    if( //individuModel.getQp3LienDeParente() == Constant.Chef_menaj_la_01 &&
                            ExpulseException==true &&    individuModel.getQp5bAge() >= Constant.AGE_50ANS
                            && NbrTotalEnfant > Constant.NBR_18_ENFANT  ){
                        throw new TextEmptyException("Moun sa a paka gen plis ke "+ Constant.NBR_18_ENFANT +" timoun.");
                    }
                }
            }

            //F2	Konbyen nan pitit sa yo ki vivan toujou?
            if (nomChamps.equalsIgnoreCase( Constant.Qf2NbrEnfantVivantGarconEtFille )) {
                /* Permettre d’effectuer un contrôle et d’établir une relation entre F.2 et F.1
                    F.2 prend une valeur dans le cas où F.1>0 contrairement il y a erreur.
                     De même F.2 ne peut être supérieur à F.1, F.2 ≤ F.1 */
                if ( ExpulseException==true &&  individuModel.getQf1aNbreEnfantNeVivantM() != null && individuModel.getQf2aNbreEnfantVivantM() != null
                        &&  individuModel.getQf1aNbreEnfantNeVivantM() == Constant.REPONS_PA_KONNEN_99
                        && individuModel.getQf2aNbreEnfantVivantM() != Constant.REPONS_PA_KONNEN_99 ) {
                    throw new TextEmptyException("Sa w mete nan F2 paka pi gwo pase sa w genyen an nan F1");
                }else if ( ExpulseException==true &&  individuModel.getQf1aNbreEnfantNeVivantM() != null && individuModel.getQf2aNbreEnfantVivantM() != null
                        &&  individuModel.getQf1aNbreEnfantNeVivantM() < individuModel.getQf2aNbreEnfantVivantM() ){
                    throw new TextEmptyException("Sa w mete nan F2 paka pi gwo pase sa w genyen an nan F1");
                }
                /* Permettre d’effectuer un contrôle et d’établir une relation entre F.2 et F.1
                    F.2 prend une valeur dans le cas où F.1>0 contrairement il y a erreur.
                     De même F.2 ne peut être supérieur à F.1, F.2 ≤ F.1 */
                if ( ExpulseException==true && individuModel.getQf1bNbreEnfantNeVivantF() !=null && individuModel.getQf2bNbreEnfantVivantF() !=null
                        && individuModel.getQf1bNbreEnfantNeVivantF() == Constant.REPONS_PA_KONNEN_99
                        && individuModel.getQf2bNbreEnfantVivantF() != Constant.REPONS_PA_KONNEN_99 ) {
                    throw new TextEmptyException("Sa w mete nan F2 paka pi gwo pase sa w genyen an nan F1\n.");
                }else if ( ExpulseException==true && individuModel.getQf1bNbreEnfantNeVivantF() !=null&& individuModel.getQf2bNbreEnfantVivantF() !=null
                        &&  individuModel.getQf1bNbreEnfantNeVivantF() < individuModel.getQf2bNbreEnfantVivantF() ){
                    throw new TextEmptyException("Tanpri gade repons ou mete nan F.1 an.");
                }
            }
            //F2B	Konbyen nan pitit sa yo ki vivan toujou?
            //if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qf2bNbreEnfantVivantF.columnName)) {
            //}

            //F3A	NAN TIMOUN SA YO KONBYEN KAP VIV TOUJOU NAN MENAJ LA?
            /*if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qf3aNbreEnfantVivantMenageM.columnName)) {
                *//* Permettre d’effectuer un contrôle et d’établir une relation entre P.3 =1 et F3, étant donné que P4=2
                Si P.3=1 Contrôle à faire entre F3 et le nombre de personnes dans le ménage déclarées « fils/fille » de la cheffe du ménage.
                F3= au nombre de "fils/fille" déclaré dans le  ménage.

                Introduire un contrôle sur la somme des naissances.*//*

                *//* F3≤F1, Si F3> F1, bloquer l’accès et afficher un message d’erreur. *//*
                if ( individuModel.getQf3aNbreEnfantVivantMenageM() > individuModel.getQf1aNbreEnfantNeVivantM() ){
                    throw new TextEmptyException("Kantite pitit kap viv nan menaj lan paka pi plis ke kantite pitit ki fèt tou vivan." );
                }

                *//* F3≤F2, Si F3> F2, bloquer l’accès et afficher un message d’erreur. *//*
                if ( individuModel.getQf3aNbreEnfantVivantMenageM() > individuModel.getQf2aNbreEnfantVivantM() ){
                    throw new TextEmptyException("Kantite pitit kap viv nan menaj lan paka pi plis ke kantite pitit ki toujou vivan yo." );
                }
            }*/
            //F3B	NAN TIMOUN SA YO KONBYEN KAP VIV TOUJOU NAN MENAJ LA?
            /*if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qf3bNbreEnfantVivantMenageF.columnName)) {
                 *//* F3≤F1, Si F3> F1, bloquer l’accès et afficher un message d’erreur. *//*
                if ( individuModel.getQf3bNbreEnfantVivantMenageF() > individuModel.getQf2bNbreEnfantNeVivantF() ){
                    throw new TextEmptyException("Kantite pitit kap viv nan menaj lan paka pi plis ke kantite pitit ki fèt tou vivan." );
                }

                *//* F3≤F2, Si F3> F2, bloquer l’accès et afficher un message d’erreur. *//*
                if ( individuModel.getQf3bNbreEnfantVivantMenageF() > individuModel.getQf2bNbreEnfantVivantF() ){
                    throw new TextEmptyException("Kantite pitit kap viv nan menaj lan paka pi plis ke kantite pitit ki toujou vivan yo." );
                }
            }*/
            //endregion

            return QSuivant;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static IndividuModel GetIndividu(int noOrdre, int idLienDeParente, long idMenageOuLogement, int Type_FORMULAIRE) {
        try {
            if( Type_FORMULAIRE == Constant.FORMULAIRE_MENAGE ) {
                return queryRecordMngr.searchIndividu_ByNoOrdre_ByIdLienDeParente_ByIdMenage(noOrdre, idLienDeParente, idMenageOuLogement);
            }else if( Type_FORMULAIRE == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                return queryRecordMngr.searchIndividu_ByNoOrdre_ByIdLienDeParente_ByIdLogement(noOrdre, idLienDeParente, idMenageOuLogement);
            }
            return null;
        } catch (ManagerException e) {
            ToastUtility.LogCat( "ManagerException: GetIndividu() :", e);
            return null;
        }
    }

    private static int GetCountMarie_Ou_Madanm(int idLienDeParente, long idMenageOuLogement, int Type_FORMULAIRE) {
        try {
            if( Type_FORMULAIRE == Constant.FORMULAIRE_MENAGE ) {
                return queryRecordMngr.CountMarie_Ou_Madanm_ByIdLienDeParente_ByIdMenage( idLienDeParente, idMenageOuLogement);
            }else if( Type_FORMULAIRE == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                return queryRecordMngr.CountMarie_Ou_Madanm_ByIdLienDeParente_ByIdLogement( idLienDeParente, idMenageOuLogement);
            }
            return 0;
        } catch (ManagerException e) {
            ToastUtility.LogCat( "ManagerException: GetCountMarie_Ou_Madanm():", e);
            return 0;
        }
    }

    public static IndividuModel GetIndividu(int noOrdre, long idMenage) {
        try {
            return queryRecordMngr.searchIndividu_ByNoOrdre_ByIdMenage(noOrdre, idMenage,false);
        } catch (ManagerException e) {
            ToastUtility.LogCat("E", "ManagerException: GetIndividu() | getMessage:" + e.getMessage() + " /n toString:" + e.toString());
            return null;
        }
    }
    //endregion
}

