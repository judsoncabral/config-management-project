<template>
  <v-container>
    <v-card elevation="2" outlined style="max-width: 600px; margin: 0 auto;">
      <v-col class="fill-height">
        <v-card-title>
          <h2>Conta: {{ id }}</h2>
        </v-card-title>
        <v-card-text>
          <p>Agência: {{ agency }}</p>
          <p>Saldo: R${{ saldo }},00</p>
          <p>Saldo Bonus: R${{ saldoBonus }},00</p>
        </v-card-text>
      </v-col>
      <v-col>
        <v-card-actions>
          <v-btn color="deep-purple darken-1" text>
            Debitar
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn icon color="deep-purple darken-1" @click="openDebitar">
            <v-icon>{{
              showDebitar ? "mdi-chevron-up" : "mdi-chevron-down"
            }}</v-icon>
          </v-btn>
        </v-card-actions>
        <v-col>
          <v-expand-transition>
            <v-row v-show="showDebitar">
              <v-text-field
                class="input-field"
                type="number"
                label="Valor"
                :rules="[(value) => !!value || 'Campo obrigatório.']"
                hide-details="auto"
                v-model="valorParaDebitar"
              ></v-text-field>
              <v-btn
                class="button-field"
                color="deep-purple darken-1"
                dark
                @click="debitar"
              >
                Confirmar
              </v-btn>
            </v-row>
          </v-expand-transition>
        </v-col>
        <v-divider></v-divider>
      </v-col>
      <v-col>
        <v-card-actions>
          <v-btn color="deep-purple darken-1" text>
            Depositar
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn icon color="deep-purple darken-1" @click="openCreditar">
            <v-icon>{{
              showCreditar ? "mdi-chevron-up" : "mdi-chevron-down"
            }}</v-icon>
          </v-btn>
        </v-card-actions>
        <v-col>
          <v-expand-transition>
            <v-row v-show="showCreditar">
              <v-text-field
                class="input-field"
                type="number"
                label="Valor"
                :rules="[(value) => !!value || 'Campo obrigatório.']"
                hide-details="auto"
                v-model="valorParaCreditar"
              ></v-text-field>
              <v-btn
                class="button-field"
                color="deep-purple darken-1"
                dark
                @click="creditar"
              >
                Confirmar
              </v-btn>
            </v-row>
          </v-expand-transition>
        </v-col>
        <v-divider></v-divider>
      </v-col>
      <v-col>
        <v-card-actions>
          <v-btn color="deep-purple darken-1" text>
            Transferir
          </v-btn>

          <v-spacer></v-spacer>

          <v-btn icon color="deep-purple darken-1" @click="openTransferir">
            <v-icon>{{
              showTransferir ? "mdi-chevron-up" : "mdi-chevron-down"
            }}</v-icon>
          </v-btn>
        </v-card-actions>
        <v-col>
          <v-expand-transition>
            <v-row v-show="showTransferir">
              <v-text-field
                class="input-field"
                type="number"
                label="Conta destino"
                :rules="[(value) => !!value || 'Campo obrigatório.']"
                hide-details="auto"
                v-model="contaDestino"
              ></v-text-field>
              <v-text-field
                class="input-field"
                type="number"
                label="Valor"
                :rules="[(value) => !!value || 'Campo obrigatório.']"
                hide-details="auto"
                v-model="valorParaTransferir"
              ></v-text-field>
              <v-btn
                class="button-field"
                color="deep-purple darken-1"
                dark
                @click="transferir"
              >
                Confirmar
              </v-btn>
            </v-row>
          </v-expand-transition>
        </v-col>
      </v-col>
    </v-card>
  </v-container>
</template>

<script>
export default {
  name: "ContaCard",
  data: () => ({
    showDebitar: false,
    showCreditar: false,
    showTransferir: false,
    valorParaDebitar: null,
    valorParaCreditar: null,
    valorParaTransferir: null,
    contaDestino: null,
  }),
  props: {
    id: Number,
    agency: Number,
    saldo: Number,
    saldoBonus: Number,
  },
  methods: {
    openTransferir() {
      this.showTransferir = !this.showTransferir;
      this.showDebitar = false;
      this.showCreditar = false;
    },
    openDebitar() {
      this.showDebitar = !this.showDebitar;
      this.showTransferir = false;
      this.showCreditar = false;
    },
    openCreditar() {
      this.showCreditar = !this.showCreditar;
      this.showDebitar = false;
      this.showTransferir = false;
    },
    async debitar() {
      try {
        await fetch("http://localhost:8080/contas/debitar", {
          body: JSON.stringify({
            contaId: this.id,
            valorParaDebitar: this.valorParaDebitar,
          }),
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        });
        this.$emit("fetchContas");
      } catch (e) {
        console.error(e);
      }
    },
    async creditar() {
      try {
        await fetch("http://localhost:8080/contas/creditar", {
          body: JSON.stringify({
            contaId: this.id,
            valorParaCreditar: this.valorParaCreditar,
          }),
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        });
        this.$emit("fetchContas");
      } catch (e) {
        console.error(e);
      }
    },
    async transferir() {
      try {
        await fetch("http://localhost:8080/contas/transferir", {
          body: JSON.stringify({
            contaOrigemId: this.id,
            contaDestinoId: this.contaDestino,
            valorParaTransferir: this.valorParaTransferir,
          }),
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
        });
        this.$emit("fetchContas");
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.input-field {
  padding: 16px;
  max-width: 200px;
}
.button-field {
  align-self: center;
}
</style>
