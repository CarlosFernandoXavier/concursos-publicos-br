package com.concursospublicosbr.enums;

public enum UnidadeFederativaEnum {
    ac, al, ap, am, ba, ce, df, es, go, ma, mt, ms, mg, pa, pb, pr, pe, pi, rj, rn, rs, ro, rr, sc, sp, se, to;

    public static boolean ehUnidadeFederativaValida(String uf) {
        if (uf == null || uf.isBlank()) {
            return false;
        }
        try {
            UnidadeFederativaEnum.valueOf(uf.toLowerCase(java.util.Locale.ROOT));
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
