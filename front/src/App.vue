<template>
  <v-app style="background-color: #EEEEEE">
    <v-app-bar app color="deep-purple darken-1" dark>
      <div class="app-title d-flex align-center">
        <v-icon large>
          mdi-wallet-plus
        </v-icon>
        <h1>NuBanco</h1>
      </div>
    </v-app-bar>

    <v-main>
      <ContaCard
        v-for="conta in contas"
        :key="conta.id"
        :id="conta.id"
        :agency="conta.agencia.id"
        :saldo="conta.saldo"
        :saldoBonus="conta.saldoBonus"
        @fetchContas="fetchContas"
      ></ContaCard>
    </v-main>
  </v-app>
</template>

<script>
import ContaCard from "./components/ContaCard";

export default {
  name: "App",

  components: {
    ContaCard,
  },

  data: () => ({
    contas: [],
  }),
  mounted() {
    this.fetchContas();
  },
  methods: {
    async fetchContas() {
      const result = await fetch("http://localhost:8080/contas");
      const contas = await result.json();
      this.contas = contas;
    },
  },
};
</script>

<style lang="scss" scoped>
.app-title {
  width: 100%;
  h1 {
    font-weight: normal;
    margin-left: 20px;
  }
}
</style>
