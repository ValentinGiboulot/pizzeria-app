import sha256 from 'js-sha256'

export class ClientService {

    constructor($http, API_URL, $q, localStorageService) {
        this.$http = $http;
        //Service de promesse fournit par angular
        this.$q = $q;
        this.API_URL = API_URL;
        this.localStorageService = localStorageService;
    }

    getClient(id) {
        return (id) ?
            this.$http.get(`${this.API_URL}/clients/${id}`)
            .then(response => response.data) :
            Promise.resolve({});
    }


    saveClient(client) {
        return (client.id) ?
            this.$http.put(`${this.API_URL}/clients/${client.id}`, client) :
            this.$http.post(`${this.API_URL}/clients`, client)
            .then(response => response.data);
    }

    deleteClient(client) {
        return this.$http.delete(`${this.API_URL}/clients/${client.id}`)
            .then(response => response.data);
    }

    // récupération du client connecté
    getConnectedClient() {
        let utilisateur = this.localStorageService.get('utilisateur', "localStorage");


        if(!utilisateur)
            return null
        return this.getClient(parseInt(utilisateur.id));
    }


    verifierUtilisateur(email, motDePasse) {
        return this.$http.get(`${this.API_URL}/clients?email=${email}&motDePasse=${sha256(motDePasse)}`)
            .then(resp => {
                this.localStorageService.set("token", resp.headers().token, "localStorage");
                return resp.data
            });
    }

    emailExiste(email) {
       
        return this.$http.get(`${this.API_URL}/clients/email?value=${email}`)
            .then(resp => resp.data)
            ;
    }

    testMdpUtilisateur(id, motDePasse) {        
        
        return this.$http.get(`${this.API_URL}/clients/verifPwd?id=${id}&motDePasse=${sha256(motDePasse)}`)
            .then(resp => resp.data)
           ;

    }



}