(defproject clj-wikipedia "0.1.0-SNAPSHOT"
  :description "A library to consume wikipedia realtime updates"
  :url "http://github.com/gbuisson/clj-wikipedia"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [irclj "0.5.0-alpha4"]
                 [danlentz/clj-uuid "0.1.6"]]

  :main clj-wikipedia.core)
