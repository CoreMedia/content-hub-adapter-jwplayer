import ContentHub_properties from "@coremedia/studio-client.main.content-hub-editor-components/ContentHub_properties";
import CopyResourceBundleProperties from "@coremedia/studio-client.main.editor-components/configuration/CopyResourceBundleProperties";
import StudioPlugin from "@coremedia/studio-client.main.editor-components/configuration/StudioPlugin";
import Config from "@jangaroo/runtime/Config";
import ConfigUtils from "@jangaroo/runtime/ConfigUtils";
import resourceManager from "@jangaroo/runtime/l10n/resourceManager";
import ContentHubJWPlayer_properties from "./ContentHubJWPlayer_properties";

interface ContentHubJWPlayerStudioPluginConfig extends Config<StudioPlugin> {
}

class ContentHubJWPlayerStudioPlugin extends StudioPlugin {
  declare Config: ContentHubJWPlayerStudioPluginConfig;

  static readonly xtype: string = "com.coremedia.labs.plugins.contenthub.jwplayer.ContentHubJWPlayerStudioPlugin";

  constructor(config: Config<ContentHubJWPlayerStudioPlugin> = null) {
    super(ConfigUtils.apply(Config(ContentHubJWPlayerStudioPlugin, {

      configuration: [
        new CopyResourceBundleProperties({
          destination: resourceManager.getResourceBundle(null, ContentHub_properties),
          source: resourceManager.getResourceBundle(null, ContentHubJWPlayer_properties),
        }),
      ],

    }), config));
  }
}

export default ContentHubJWPlayerStudioPlugin;
