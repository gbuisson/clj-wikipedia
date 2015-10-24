(ns clj-wikipedia.core
  (:require [irclj.core :as irc]
            [clj-uuid :as uuid]))

(def server "irc.wikimedia.org")
(def port 6667)


(def langs ["en"
            "sv"
            "de"
            "nl"
            "fr"
            "ru"
            "war"
            "ceb"
            "it"
            "es"
            "vi"
            "pl"
            "ja"
            "pt"
            "zh"
            "uk"
            "ca"
            "fa"
            "sh"
            "no"
            "ar"
            "fi"
            "id"
            "hu"
            "ro"
            "cs"
            "ko"
            "sr"
            "ms"
            "tr"
            "min"
            "eo"
            "kk"
            "eu"
            "da"
            "bg"
            "sk"
            "hy"
            "he"
            "it"
            "hr"
            "sl"
            "et"
            "uz"
            "gl"
            "nn"
            "la"
            "vo"
            "simple"
            "el"
            "be"
            "ce"
            "hi"
            "ka"
            "az"
            "th"
            "oc"
            "ur"
            "mk"
            "mg"
            "new"
            "ta"
            "cy"
            "tt"
            "bs"
            "pms"
            "tl"
            "lv"
            "te"
            "zh-min-nan"
            "be-x-old"
            "br"
            "ht"
            "sq"
            "jv"
            "ky"
            "lb"
            "mr"
            "zh-yue"
            "ml"
            "is"
            "tg"
            "bn"
            "af"
            "ga"
            "ba"
            "ast"
            "sco"
            "pnb"
            "cv"
            "fy"
            "lmo"
            "my"
            "yo"
            "an"
            "sw"
            "ne"
            "io"
            "gu"
            "scn"
            "bpy"
            "nds"
            "ku"
            "als"
            "qu"
            "pa"
            "su"
            "kn"
            "ckb"
            "bar"
            "mn"
            "ia"
            "arz"
            "nap"
            "bug"
            "bat-smg"
            "wa"
            "gd"
            "am"
            "map-bms"
            "yi"
            "mzn"
            "fo"
            "si"
            "nah"
            "vec"
            "sah"
            "li"
            "os"
            "mrj"
            "sa"
            "or"
            "hsb"
            "roa-tara"
            "pam"
            "mhr"
            "ilo"
            "se"
            "mi"
            "azb"
            "bcl"
            "hif"
            "hak"
            "gan"
            "ps"
            "diq"
            "bh"
            "glk"
            "rue"
            "nds-nl"
            "vls"
            "bo"
            "fiu-vro"
            "xmf"
            "co"
            "tk"
            "eml"
            "sc"
            "vep"
            "sd"
            "lrc"
            "gv"
            "km"
            "csb"
            "kv"
            "crh"
            "zea"
            "frr"
            "zh-classical"
            "wuu"
            "as"
            "so"
            "szl"
            "udm"
            "ay"
            "kw"
            "stq"
            "nrm"
            "rm"
            "koi"
            "lad"
            "cdo"
            "ie"
            "fur"
            "mt"
            "pcd"
            "gn"
            "dv"
            "dsb"
            "lij"
            "cbk-zam"
            "ksh"
            "myv"
            "gag"
            "ext"
            "mwl"
            "ang"
            "lez"
            "nso"
            "ace"
            "ug"
            "pi"
            "pag"
            "kab"
            "kab"
            "nv"
            "frp"

            "sn"
            "ln"
            "av"
            "pfl"
            "haw"
            "xal"
            "krc"
            "kaa"
            "rw"
            "pdc"
            "to"
            "kl"
            "arc"
            "nov"
            "kbd"
            "gom"
            "mai"
            "bxr"
            "lo"
            "bjn"
            "ha"
            "tet"
            "pap"
            "tpi"
            "na"
            "tyv"
            "lbe"
            "jbo"
            "roa-rup"
            "ty"
            "mdf"
            "za"
            "ig"
            "wo"
            "srn"
            "kg"
            "ab"
            "ltg"
            "zu"
            "om"
            "lg"
            "rmy"
            "chy"
            "cu"
            "tw"
            "tn"
            "chr"
            "bi"
            "pih"
            "sm"
            "xh"
            "ss"
            "got"
            "rn"
            "ki"
            "pnt"
            "bm"
            "iu"
            "ee"
            "st"
            "ts"
            "ks"
            "fj"
            "ak"
            "sg"
            "ik"
            "ve"
            "ff"
            "ny"
            "ti"
            "ch"
            "dz"
            "tum"
            "cr"])

(defn nickname []
  (str "clj-wikipedia-" (uuid/v1)))

(defn handle-message [irc s]
  (let [source (-> s :params first)
        clean-text (clojure.string/replace (:text s)
                                           #"[\x02\x1F\x0F\x16]|\x03(\d\d?(,\d\d?)?)?" "")]
    (println source clean-text)))

(defn lang->channel [l]
  (str "#" l ".wikipedia"))

(defn listen-one [lang]

  (println "listen: " lang)
  (let [connection (irc/connect
                    server
                    port
                    (nickname) :callbacks {:privmsg handle-message})]
    (irc/join connection (lang->channel lang))))

(defn -main []
  (dorun (pmap listen-one langs)))
