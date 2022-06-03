(ns spootnik.deps-check
  "Inspired from https://github.com/athos/clj-check"
  (:require [bultitude.core  :as bultitude]
            [clojure.java.io :as io]
            [clojure.string  :as str]))

(defn- file-for
  [ns]
  (-> ns
      name
      (str/replace \- \_)
      (str/replace \. \/)))

(defn- check-ns
  [ns]
  (println "compiling namespace" ns)
  (try
    (binding [*warn-on-reflection* true]
      (load (file-for ns)))
    nil
    (catch Exception e
      (binding [*out* *err*]
        (println "failed to load namespace" ns ":" (ex-message (ex-cause e)))
        ns))))

(defn- find-namespaces
  [dirs]
  (bultitude/namespaces-on-classpath
   :classpath (map io/file dirs)
   :ignore-unreadable? false))

(defn check
  [{:keys [paths] :as opts}]
  (let [namespaces (find-namespaces paths)
        errored-namespaces (into [] (remove nil?) (map check-ns namespaces))]
    (when (seq errored-namespaces)
      (binding [*out* *err*]
        (println "the following namespaces failed to load:" errored-namespaces)
        (System/exit 1)))
    (shutdown-agents))
  opts)
