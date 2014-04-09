using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class TipoPortaMap : EntityTypeConfiguration<TipoPorta>
    {
        public TipoPortaMap()
        {
            ToTable("TIPO_PORTA_MODULO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_TIPO_POTA_MODULO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Tipo).HasColumnName("DS_TIPO").IsRequired();
            Property(e => e.Identificador).HasColumnName("DS_IDENTIFICADOR").IsRequired();

            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
        
        }
    }
}
