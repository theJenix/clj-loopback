(ns clj-loopback.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body wrap-json-params]]
            [ring.util.response :refer [response]]
            [ring.util.codec :refer [form-decode]]
            [clojure.walk :refer [keywordize-keys]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defn init []
  (println "clj-loopback is starting"))

(defn destroy []
  (println "clj-loopback is shutting down"))


(defn handle-loopback [req]
  (let [add (or (System/getenv "ADD_TO_RESPONSE") "")
        add-params (keywordize-keys (form-decode add))]
    (-> req
        (dissoc :body)
        (merge add-params)
        response)))

(def app
  (-> handle-loopback
      wrap-json-response
      (handler/site)
      (wrap-base-url)))
