const { jangarooConfig } = require("@jangaroo/core");

module.exports = jangarooConfig({
  type: "code",
  sencha: {
    name: "com.coremedia.labs.plugins__studio-client.content-hub-adapter-jwplayer",
    namespace: "com.coremedia.labs.plugins.adapters.jwplayer.client",
    studioPlugins: [
      {
        mainClass: "com.coremedia.labs.plugins.adapters.jwplayer.client.ContentHubJWPlayerStudioPlugin",
        name: "Content Hub - JW Player",
      },
    ],
  },
  command: {
    build: {
      ignoreTypeErrors: true
    },
  },
});
