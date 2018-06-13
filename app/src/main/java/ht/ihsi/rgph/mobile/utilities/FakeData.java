package ht.ihsi.rgph.mobile.utilities;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.models.RowDataListModel;

/**
 * Created by ajordany on 4/14/2016.
 */
public class FakeData {

    public static List<RowDataListModel> prepareIndividu(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Individi-1 : Junior Raymond, sexe: M","011101122 ->Batiman-1 ->Lojman Individyèl-1 ->Menaj-1");
        row.setIsModuleMenu(false);
        rows.add(row);

        row= new RowDataListModel("","Individi-2 : Arold Jean, sexe: M","011101122 ->Batiman-1 ->Lojman Individyèl-1 ->Menaj-1");
        row.setIsModuleMenu(false);
        rows.add(row);

        row= new RowDataListModel("","Individi-3 : Joune Paul, sexe: F","011101122 ->Batiman-1 ->Lojman Individyèl-1 ->Menaj-1");
        row.setIsModuleMenu(false);
        row.setIsComplete(false);
        rows.add(row);


        return rows;
    }

    public static List<RowDataListModel> prepareDeces(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Deces-1, sexe: M","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);

        row= new RowDataListModel("","Deces-2, sexe: M","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);

        row= new RowDataListModel("","Deces-3, sexe: M","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);

        return rows;
    }

    public static List<RowDataListModel> prepareEmigre(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Emigre-1 : Jean Franzt, sexe: M","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);

        row= new RowDataListModel("","Emigre-2 : Mirlande Arnaud, sexe: F","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);

        row= new RowDataListModel("","Emigre-3 : Jean Jeames, sexe: M","011101122/Batiman-1/Lojman Individyèl-1-1/Menaj-1");
        rows.add(row);


        return rows;
    }

    public static List<RowDataListModel> prepareMenage(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Menaj-1","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-2","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-3","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-4","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-5","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-6","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-7","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-8","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","MENAJ-9","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);

        row= new RowDataListModel("","Menaj-10","011101122/Batiman-1/Lojman Individyèl-1");
        rows.add(row);
        return rows;
    }

    public static List<RowDataListModel> prepareLogementInd(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Lojman Individyèl-1","Ouest/Port-au-Prince/011101121");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-2","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-3","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-4","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-5","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-6","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-7","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-8","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-9","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman Individyèl-10","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);
        return rows;
    }

    public static List<RowDataListModel> prepareLogementCollectif(){
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Lojman kolektif-1","Ouest/Port-au-Prince/011101121");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-2","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-3","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-4","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-5","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-6","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-7","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-8","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-9","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);

        row= new RowDataListModel("","Lojman kolektif-10","Ouest/Port-au-Prince/011101122/Batiman-1");
        rows.add(row);
        return rows;
    }

    public static List<RowDataListModel> prepareBatimentIncompletData() {
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Batiman-1 | REC:001","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-2 | Rec:002","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-3 | Rec:003","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-4 | Rec:004","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-5 | Rec:005","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-6 | Rec:006","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-7 | Rec:007","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-8 | Rec:008","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-9 | Rec:009","Ouest->Port-au-Prince->011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-10 | Rec:010","Ouest->Port-au-Prince->011101121");
        rows.add(row);
        return rows;
    }

    public static List<RowDataListModel> prepareBatimentMalRempliData() {
        List<RowDataListModel> rows= new ArrayList<>();
        RowDataListModel row= new RowDataListModel("","Batiman-1 | REC:001","Ouest / Port-au-Prince / 011101121");
        rows.add(row);

        row= new RowDataListModel("","Batiman-11 | Rec:002","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-12 | Rec:003","Sud-est /Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-13 | Rec:004","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-14 | Rec:005","Sud-est / Jacmel/ 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-15 | Rec:006","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-16 | Rec:007","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-17 | Rec:008","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-11 | Rec:002","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-12 | Rec:003","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-13 | Rec:004","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-14 | Rec:005","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-15 | Rec:006","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-16 | Rec:007","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-17 | Rec:008","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-11 | Rec:002","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-12 | Rec:003","Sud-est / Jacmel / 0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-13 | Rec:004","Sud-est/Jacmel/0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-14 | Rec:005","Sud-est/Jacmel/0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-15 | Rec:006","Sud-est/Jacmel/0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-16 | Rec:007","Sud-est/Jacmel/0111011254");
        rows.add(row);

        row= new RowDataListModel("","Batiman-17 | Rec:008","Sud-est/Jacmel/0111011254");
        rows.add(row);

        rows.add(row);
        return rows;
    }
}
